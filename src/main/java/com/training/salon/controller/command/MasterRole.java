package com.training.salon.controller.command;

import javax.servlet.http.HttpServletRequest;

public class MasterRole implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/master/masterbasic.jsp";
    }
}
