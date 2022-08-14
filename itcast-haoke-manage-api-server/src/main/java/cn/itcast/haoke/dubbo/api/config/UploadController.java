package cn.itcast.haoke.dubbo.api.config;

import cn.itcast.haoke.dubbo.api.pojo.PicResultVo;
import cn.itcast.haoke.dubbo.api.service.PicUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("pic/upload")
@Controller
public class UploadController {
    @Autowired
    private PicUploadService picUploadService;

    @PostMapping
    @ResponseBody
    public PicResultVo upload(@RequestParam("file")  MultipartFile uploadFile) {
        return this.picUploadService.upload(uploadFile);
    }
}
