package io.kevin.common.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * xss过滤
 * @author ZGJ
 * @date 2017/7/1 14:16
 **/
public class XssFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        XssHttpServletRequestWapper xssRequest = new XssHttpServletRequestWapper((HttpServletRequest)servletRequest);
        filterChain.doFilter(xssRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
