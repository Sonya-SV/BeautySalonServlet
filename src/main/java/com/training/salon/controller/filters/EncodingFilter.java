package com.training.salon.controller.filters;


import javax.servlet.*;
import java.io.IOException;
import java.util.Locale;
import java.util.TimeZone;

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

//        Locale locale = new Locale("uk_UA");
        Locale locale = new Locale("us_US");

        String language = locale.getLanguage();

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }
}
