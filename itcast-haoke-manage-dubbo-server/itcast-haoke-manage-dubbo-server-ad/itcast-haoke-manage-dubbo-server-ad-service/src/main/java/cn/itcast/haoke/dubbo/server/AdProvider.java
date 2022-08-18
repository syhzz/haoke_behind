package cn.itcast.haoke.dubbo.server;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AdProvider {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AdProvider.class).web(WebApplicationType.NONE).run(args);
    }
}
