package order.mm.com.coomm;

import lombok.extern.slf4j.Slf4j;
import order.mm.com.bean.Qrcode;
import order.mm.com.mapper.QrCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

@Component
@Slf4j
public class Cache {

    public static Map<String, Map<Integer, Qrcode>> qrcodeMap = new ConcurrentHashMap<>();

    @Autowired
    QrCodeMapper qrCodeMapper;

    public static Map<String, Map<Integer, Qrcode>> unUseMap = new ConcurrentHashMap<>();

    ExecutorService poolExecutor = Executors.newSingleThreadExecutor();

    public ReentrantLock lock = new ReentrantLock();


    @PostConstruct
    public void load() {
        try {
            List<Qrcode> qrcodeList = qrCodeMapper.slectAll();
            Map<String, Map<Integer, Qrcode>> map = new HashMap<>();
            for (Qrcode qrcode : qrcodeList) {
                Map<Integer, Qrcode> typeList = map.get(qrcode.type);
                if (typeList == null) {
                    typeList = new HashMap<>();
                    map.put(qrcode.type, typeList);
                }
                typeList.put(qrcode.id, qrcode);
                setToMap(qrcode);
            }
            qrcodeMap = map;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setToMap(Qrcode qrcode) {
        if (qrcode.status == Qrcode.status_WAIT_PAY) {
            Map<Integer, Qrcode> qrcodeMap = unUseMap.get(qrcode.type);
            if (qrcodeMap != null) {
                qrcodeMap.remove(qrcode.id);
            }
        } else if (qrcode.status == Qrcode.status_WAIT_USE) {
            Map<Integer, Qrcode> qrcodeMap = unUseMap.get(qrcode.type);
            if (qrcodeMap == null) {
                qrcodeMap = new HashMap<>();
                unUseMap.put(qrcode.type, qrcodeMap);
            }
            qrcodeMap.put(qrcode.id, qrcode);
        }
    }

    public void updateCache(Qrcode qrcode) {
        try {
        boolean isLock = lock.tryLock(10, TimeUnit.SECONDS);
        setToMap(qrcode);
        if (isLock) {
            lock.unlock();
        }
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                qrCodeMapper.updateStatus(qrcode.status, qrcode.id);
            }
        });
        }catch (Exception e){
            throw new AppException("服务器繁忙请稍后","500");
        }finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }

    public Qrcode genQrcode(String type)  {
        try {
            boolean isLock = lock.tryLock(10, TimeUnit.SECONDS);

            Map<Integer, Qrcode> allTypeQrs = unUseMap.get(type);
            if (allTypeQrs == null || allTypeQrs.isEmpty()) {
                throw new AppException("该渠道维护中","200");
            }
            log.debug(" genQrcode size= "+allTypeQrs.values().size());
            List<Qrcode> qrcodeList = new ArrayList<>();
            for (Qrcode qrcode : allTypeQrs.values()) {
                qrcodeList.add(qrcode);
            }
            int index = new Random().nextInt(qrcodeList.size());
            Qrcode qrcode = qrcodeList.get(index);
            return qrcode;
        }catch (Exception e){
            throw new AppException(e.getMessage(),"500");
        }finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Random().nextInt(1));
    }
}
