package com.training.salon.controller.filters;

import com.training.salon.model.entity.User;

import javax.jws.soap.SOAPBinding;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.isNull;

public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user= (User) request.getSession().getAttribute("user");
        User.Role role = (User.Role) request.getSession().getAttribute("role");

        String path = request.getRequestURI();
        if( (path.contains("/user")  && user==null) ||
                (path.contains("/admin") && role!=User.Role.ADMIN)||
                (path.contains("/master/") && (role==User.Role.USER || role ==null))){
            request.getRequestDispatcher("/beauty-salon/").forward(request, response);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
