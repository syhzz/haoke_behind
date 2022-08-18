package cn.itcast.haoke.dubbo.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:mock-data.properties")
@ConfigurationProperties(prefix = "mock")
@Data
public class MockConfig {
    private String indexMenu;

    private String indexInfo;

    private String indexFaq;

    private String indexHouse;

    private String infoList1;

    private String infoList2;

    private String infoList3;

    private String my;

}
