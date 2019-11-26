package com.training.salon.controller.command;

import javax.servlet.http.HttpServletRequest;

public class SuccessPageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/"+request.getSession().getAttribute("role").toString().toLowerCase()+"/successpage.jsp";
    }
}
