package order.mm.com.controller;

import order.mm.com.bean.Attach;
import order.mm.com.bean.Order;
import order.mm.com.service.PublicService;
import order.mm.com.vo.resp.UploadResp;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.Serializable;

@Controller
@RequestMapping("boss/public")
public class PublicController implements Serializable {

    @Autowired
    PublicService publicService;

    @RequestMapping("/fileUpload")
    @ResponseBody
    public UploadResp fileUpload(@Param("file") MultipartFile file) {
        UploadResp resp = new UploadResp();
        resp.code = "0";
        if (file.isEmpty()) {
            return new UploadResp();
        }
        String fileName = file.getOriginalFilename();
        try {
            byte[] body = file.getBytes();
            Attach attach = publicService.saveAttach(fileName, body);
            resp.id = attach.id;
        } catch (Exception e) {
            e.printStackTrace();
            resp.code = "-2";
            return resp;
        }
        return resp;
    }

    @RequestMapping("/attach/{id}")
    public void fileUpload(@PathVariable("id") String id, HttpServletResponse response) {
        try {
            Attach attach = publicService.getAttach(id);
            OutputStream out = response.getOutputStream();
            response.setHeader("Content-Type", "image/jpg");
            out.write(attach.body);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/attach/test")
    @ResponseBody
    public Object tset(@PathVariable("id") String id, HttpServletResponse response) {
        Order order=new Order();
        order.attachId="sssss";
        return order;
    }
}
