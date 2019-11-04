package com.training.salon.controller.command;

import javax.servlet.http.HttpServletRequest;

public class UserRole implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/user/userbasic.jsp";
    }
}
