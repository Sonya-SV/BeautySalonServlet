package com.training.salon.controller.command;

import com.training.salon.model.entity.User;
import com.training.salon.model.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;


import static java.util.Objects.nonNull;

public class Registration implements ICommand {
    private  static final Logger log = LogManager.getLogger(Registration.class);
    private UserService userService;

    public Registration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(Optional.ofNullable( (String) request.getSession().getAttribute("lang")).orElse("en")));
        if (nonNull(request.getSession().getAttribute("user")))
            return "redirect:/";

        if (Optional.ofNullable(request.getParameter("email")).isEmpty() ) return "/registration.jsp";

        User user = User.builder()
                .email(request.getParameter("email"))
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .password(BCrypt.hashpw(request.getParameter("password"),BCrypt.gensalt(8)))
                .role(User.Role.USER)
                .build();

        try {
            userService.saveUser(user);
            log.info("New user " + user.getEmail() + " was successfully registered");
        }catch (SQLException e){
            log.warn("User " + user.getEmail() + " wasn't registered. Not unique email");
            request.setAttribute("errorMessage", bundle.getString("not.unique.email"));
            return "/registration.jsp";
        }
        return "redirect:/login";
    }
}
