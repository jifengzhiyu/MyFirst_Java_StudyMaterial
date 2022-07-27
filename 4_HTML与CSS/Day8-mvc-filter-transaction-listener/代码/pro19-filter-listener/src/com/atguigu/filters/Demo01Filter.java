package com.atguigu.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/demo01.do")
@WebFilter("*.do")
public class Demo01Filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("helloA");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("helloA2");
    }

    @Override
    public void destroy() {

    }
}
