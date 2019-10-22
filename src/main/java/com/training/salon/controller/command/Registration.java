package com.training.salon.controller.command;

import com.training.salon.model.entity.User;
import com.training.salon.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class Registration implements ICommand {
    private UserService userService;

    public Registration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        if (email == null || email.equals("") || pass == null || pass.equals("")) {
            return "/registration.jsp";
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(pass);
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setRole(User.Role.USER);
        try {
            userService.saveUser(user);
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Not unique email");
            return "/registration.jsp";
        }
        return "redirect:/login.jsp";
    }
}
