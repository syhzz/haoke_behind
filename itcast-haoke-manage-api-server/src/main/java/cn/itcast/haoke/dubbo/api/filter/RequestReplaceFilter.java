package cn.itcast.haoke.dubbo.api.filter;

import cn.itcast.haoke.dubbo.api.wrapper.MyRequestWrapper;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestReplaceFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (!(httpServletRequest instanceof MyRequestWrapper)) {
            httpServletRequest = new MyRequestWrapper(httpServletRequest);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
