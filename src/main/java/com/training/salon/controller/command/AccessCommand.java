package com.training.salon.controller.command;

import com.training.salon.model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class AccessCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("role")==null)
            return "/index.jsp";
        else if (request.getSession().getAttribute("role").equals(User.Role.ADMIN))
            return "redirect:/admin";
        else if (request.getSession().getAttribute("role").equals(User.Role.MASTER))
            return "redirect:/master";
        else
            return "redirect:/user";
    }
}
