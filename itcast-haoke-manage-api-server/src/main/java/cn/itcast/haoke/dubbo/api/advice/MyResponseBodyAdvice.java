package cn.itcast.haoke.dubbo.api.advice;

import cn.itcast.haoke.dubbo.api.Interceptor.RedisCacheInterceptor;
import cn.itcast.haoke.dubbo.api.controller.GraphController;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.GraphQL;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;

public class MyResponseBodyAdvice implements ResponseBodyAdvice {
    @Autowired
    private RedisTemplate redisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        if (methodParameter.hasMethodAnnotation(GetMapping.class)) {
            return true;
        }
        if (methodParameter.hasMethodAnnotation(PostMapping.class) &&
                StringUtils.equals(GraphController.class.getName(), methodParameter.getExecutable().getDeclaringClass().getName())) {
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        try {
            String redisKey = RedisCacheInterceptor.createRedisKey(((ServletServerHttpRequest) serverHttpRequest).getServletRequest());
            String redisValue;
            if (o instanceof String) {
                redisValue = (String) o;
            } else {
                redisValue = mapper.writeValueAsString(o);
            }
            this.redisTemplate.opsForValue().set(redisKey, redisValue);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return o;
    }
}
