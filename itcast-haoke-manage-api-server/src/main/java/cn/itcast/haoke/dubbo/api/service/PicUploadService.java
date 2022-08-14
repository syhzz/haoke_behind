package cn.itcast.haoke.dubbo.api.service;

import cn.itcast.haoke.dubbo.api.config.AliyunConfig;
import cn.itcast.haoke.dubbo.api.pojo.PicResultVo;
import com.aliyun.oss.OSS;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class PicUploadService {

    private static final String[] IMAGE_TYPE = new String[]{".bmp", ",jpg", ".jpeg", ".gif", "png"};

    @Autowired
    private OSS ossClient;

    @Autowired
    private AliyunConfig aliyunConfig;

    public PicResultVo upload(MultipartFile uploadFile) {
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), type)) {
                isLegal = true;
                break;
            }
        }

        PicResultVo picResultVo = new PicResultVo();

        if (!isLegal) {
            picResultVo.setStatus("error");
            return picResultVo;
        }

        String filename = uploadFile.getOriginalFilename();
        String filePath = getFilePath(filename);
        try {
            ossClient.putObject(aliyunConfig.getBucketName(), filePath, new ByteArrayInputStream(uploadFile.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
            picResultVo.setStatus("error");
            return picResultVo;
        }
        picResultVo.setStatus("done");
        picResultVo.setName(this.aliyunConfig.getUrlPrefix() + filePath);
        picResultVo.setUid(String.valueOf(System.currentTimeMillis()));
        return picResultVo;
    }

    private String getFilePath(String fileName) {
        DateTime dateTime = new DateTime();
        return "images/" + dateTime.toString("yyyy") + "/" + dateTime.toString("MM")
                + "/" + dateTime.toString("dd") + "/" + System.currentTimeMillis() + RandomUtils.nextInt(1000, 9999)
                + "." + StringUtils.substringAfterLast(fileName, ".");
    }
}
