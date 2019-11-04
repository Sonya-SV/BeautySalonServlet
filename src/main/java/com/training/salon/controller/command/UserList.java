package com.training.salon.controller.command;

import com.training.salon.model.entity.User;
import com.training.salon.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserList implements ICommand {

    private UserService userService;

    public UserList(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("users", userService.getAllUsers());
        return "/WEB-INF/admin/userlist.jsp";
    }
}
