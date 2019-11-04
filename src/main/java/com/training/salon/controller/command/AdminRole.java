package com.training.salon.controller.command;

import javax.servlet.http.HttpServletRequest;

public class AdminRole implements ICommand{
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/admin/adminbasic.jsp";
    }
}
