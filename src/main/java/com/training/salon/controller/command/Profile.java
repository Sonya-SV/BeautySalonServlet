package com.training.salon.controller.command;


import com.training.salon.model.entity.User;
import com.training.salon.model.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class Profile implements ICommand {
    private static final Logger log = LogManager.getLogger(Profile.class);

    private UserService userService;

    public Profile(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        User user = (User) request.getSession().getAttribute("user");
        String role = request.getSession().getAttribute("role").toString().toLowerCase();
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(Optional.ofNullable( (String) request.getSession().getAttribute("lang")).orElse("en")));
        if (Optional.ofNullable(password).isEmpty())
            return "/WEB-INF/" + role + "/" + role + "profile.jsp";

        if (password.equals(password2)) {
            try {
                userService.update(firstName, lastName, password, user);
                request.setAttribute("successSave", bundle.getString("success.save"));
                log.info("Saved new parameters for user" + user.getEmail());
            } catch (SQLException e) {
                log.warn("Cant save new parameters for user" + user.getEmail());
            }
        } else {
            request.setAttribute("passwordErrorDiffer", bundle.getString("password.different"));
        }
        return "/WEB-INF/" + role + "/" + role + "profile.jsp";
    }
}
