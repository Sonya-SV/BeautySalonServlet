package com.training.salon.controller.command;


import com.training.salon.model.entity.User;
import com.training.salon.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class Profile implements ICommand {


    public final static String PASSWORD_DIFFERENT = "Password are different";
    public final static String SUCCESS_SAVE = "Saved successfully";

    private UserService userService;

    public Profile(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        if (password == null || password.equals("") || password2 == null || password2.equals("")) {
            return "/app/user/profile.jsp";
        }
        String firstName = request.getParameter("firstName");
        String lastName =request.getParameter("lastName");
        if (password.equals(password2)) {

            try {
                userService.update(firstName, lastName, password, (User)request.getSession().getAttribute("user"));
                request.setAttribute("successSave", SUCCESS_SAVE);
            } catch (SQLException e) {
                //TODO add logger
                return "/app/user/profile.jsp";
            }
        }
        else {
            request.setAttribute("passwordErrorDiffer", PASSWORD_DIFFERENT);
            return "/app/user/profile.jsp";
        }
        return "/app/user/profile.jsp";
    }
}
