package com.training.salon.controller.filters;

import com.training.salon.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static java.util.Objects.isNull;

//TODO: add to web.xml
public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        User user= (User) request.getSession().getAttribute("user");

        if(isNull(user)) {
            user = new User();
            user.setRole(User.Role.GUEST);
        }
        String path = request.getRequestURI();
        if( path.contains("user")  && user.getRole().equals(User.Role.GUEST) ||
                path.contains("admin") && !user.getRole().equals(User.Role.ADMIN) ||
                path.contains("master/") && !user.getRole().equals(User.Role.MASTER)){
            servletResponse.getWriter().append("AccessDenied");
        }else
            filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
