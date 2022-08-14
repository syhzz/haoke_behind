package cn.itcast.haoke.dubbo.api.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:aliyun.properties")
@ConfigurationProperties(prefix = "aliyun")
@Data
public class AliyunConfig {
    String endPoint;
    String accessKeyId;
    String accessKeySecret;
    String bucketName;
    String urlPrefix;

    public OSS ossClient() {
        return new OSSClient(endPoint, accessKeyId, accessKeySecret);
    }
}
