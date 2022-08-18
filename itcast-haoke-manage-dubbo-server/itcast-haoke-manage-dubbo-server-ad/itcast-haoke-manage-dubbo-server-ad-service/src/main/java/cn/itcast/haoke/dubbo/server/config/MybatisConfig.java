package cn.itcast.haoke.dubbo.server.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("cn.itcast.haoke.dubbo.server.mapper")
@Configuration
public class MybatisConfig {
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
