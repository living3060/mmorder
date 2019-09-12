package order.mm.com.service;

import order.mm.com.bean.Attach;
import order.mm.com.mapper.AttachMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class PublicService {
    @Autowired
    AttachMapper attachMapper;

    public Attach saveAttach(String fileName, byte[] bytes) {
        int index = fileName.lastIndexOf(".");
        String fix = fileName.substring(index, fileName.length());
        Attach attach = new Attach();
        attach.createAt = new Date();
        attach.updateAt = new Date();
        attach.id = UUID.randomUUID().toString() + fix;
        attach.fileName = fileName;
        attach.body = bytes;
        attachMapper.insert(attach);
        return attach;
    }

    public Attach getAttach(String id) {
      return attachMapper.select(id);
    }
}
