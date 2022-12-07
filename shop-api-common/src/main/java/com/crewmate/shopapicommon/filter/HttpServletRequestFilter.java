package com.crewmate.shopapicommon.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.crewmate.shopapicommon.config.CustomHttpServletRequestWrapper;

@Component
public class HttpServletRequestFilter extends OncePerRequestFilter {
    /**
     * HttpServletRequest 의 InputStream(requestBody) 를 여러번 사용하기위해 재정의한 Wrapper를 request로 바꿔치기한다.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        CustomHttpServletRequestWrapper customHttpServletRequestWrapper = new CustomHttpServletRequestWrapper(request);
        
        filterChain.doFilter(customHttpServletRequestWrapper, response);
    }

}
