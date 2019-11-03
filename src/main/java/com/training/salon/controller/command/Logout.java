package com.training.salon.controller.command;

import com.training.salon.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Logout implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {

        final HttpSession session = request.getSession();
        session.invalidate();
        CommandUtility.setUserRole(request, User.Role.GUEST, "Guest");
        return "redirect:/login.jsp";
    }
}