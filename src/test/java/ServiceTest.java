import order.mm.com.bean.Attach;
import order.mm.com.bean.Order;
import order.mm.com.bean.OrderFilter;
import order.mm.com.bean.Qrcode;
import order.mm.com.mapper.AttachMapper;
import order.mm.com.mapper.OrderMapper;
import order.mm.com.mapper.QrCodeMapper;
import order.mm.com.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ServiceTest extends BaseTest {

    @Autowired
   public UserMapper userMapper;

    @Autowired
    public AttachMapper attachMapper;


    @Autowired
    public QrCodeMapper qrCodeMapper;


    @Autowired
    public OrderMapper orderMapper;

    @Test
    public void selectUserTest(){
        userMapper.selectUser("liwei");
    }

    @Test
    public void attach(){
        Attach attach=new Attach();
        attach.createAt=new Date();
        attach.updateAt=new Date();
        attach.id= UUID.randomUUID().toString();
        attach.fileName="1.jsp";
        attach.body="ssss".getBytes();
        attachMapper.insert(attach);


        Attach select=attachMapper.select(attach.id);
        System.out.println(select.body);
    }

    @Test
    public void attachMapper(){
        Qrcode qrcode=new Qrcode();
        qrcode.attachId="sssss.jpg";
        qrcode.back="hao";
        qrcode.currentOrderExp=new Date();
        qrcode.lastOrderAt=new Date();
        qrcode.status=-1;
        qrcode.todayAmount=1d;
        qrcode.qrcodeAccount="liwei";
        qrcode.totalAmount=2d;
        qrcode.updateAt=new Date();
        qrcode.createAt=new Date();

        qrCodeMapper.insert(qrcode);
        System.out.println(qrCodeMapper.slectByStatus(-1));
    }

    @Test
    public void orderMapper(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd-HH-mm-sss");
        Order order=new Order();
        order.amount=10000d;
        order.createAt=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.SECOND,-10);
        order.expTime=calendar.getTime();
        order.gameId=05;
        order.gameChannel=101;
        order.qrCodeId=01;
        order.qrCodeAccount="liwei";
        order.orderNo=simpleDateFormat.format(new Date());
        calendar.add(Calendar.SECOND,-10);
        order.hit=calendar.getTime();
          orderMapper.insert(order);
      System.out.println(order.id);
    }

    @Test
    public void select(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd-HH-mm-sss");
        Order order=new Order();
        order.amount=10000d;
        order.createAt=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.SECOND,-10);
        order.expTime=calendar.getTime();
        order.gameId=05;
        order.gameChannel=101;
        order.qrCodeId=01;
        order.qrCodeAccount="liwei";
        order.orderNo=simpleDateFormat.format(new Date());
        calendar.add(Calendar.SECOND,-10);
        order.hit=calendar.getTime();

        OrderFilter orderFilter=new OrderFilter();
        orderFilter.status=0;
        orderFilter.amount=10000d;
        orderFilter.qrCodeAccount="w";
        List<Order> o=  orderMapper.selectByFilter(orderFilter);
    }

    @Test
    public void update(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd-HH-mm-sss");
        Order order=new Order();
        order.amount=10000d;
        order.createAt=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.SECOND,-10);
        order.expTime=calendar.getTime();
        order.gameId=05;
        order.gameChannel=101;
        order.qrCodeId=01;
        order.qrCodeAccount="liwei";
        order.orderNo=simpleDateFormat.format(new Date());
        calendar.add(Calendar.SECOND,-10);
        order.hit=calendar.getTime();

        OrderFilter orderFilter=new OrderFilter();
        orderFilter.status=0;
        orderFilter.amount=10000d;
        orderFilter.qrCodeAccount="w";
        orderMapper.updateStatus(2,0,"2019-09-09-04-20-053");
    }
}
