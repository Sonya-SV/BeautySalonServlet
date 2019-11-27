package com.training.salon.controller.command;


import com.training.salon.model.entity.User;
import com.training.salon.model.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Login implements ICommand {
    private static final Logger log = LogManager.getLogger(Login.class);

    private UserService userService;

    public Login(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (nonNull(request.getSession().getAttribute("user")))
            return "redirect:/";

        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(Optional.ofNullable( (String) request.getSession().getAttribute("lang")).orElse("en")));
        if (isNull(email)) return "/login.jsp";
        Optional<User> user = userService.login(email, pass);
        if (user.isEmpty()) {
            log.info("Invalid attempt of user email: '" + email + "'");
            request.setAttribute("userError", bundle.getString("user.error"));
            return "/login.jsp";
        }
        if (CommandUtility.checkUserIsLogged(request, email)) {
            log.warn("User \"" + email + "\" is already logged");
            return "/login.jsp";
        }
        log.info("User \"" + email + "\" logged successfully");
        request.getSession().setAttribute("user", user.get());
        if (user.get().getRole().equals(User.Role.ADMIN)) {
            CommandUtility.setUserRole(request, User.Role.ADMIN, email);
            return "redirect:/admin";
        } else if (user.get().getRole().equals(User.Role.MASTER)) {
            CommandUtility.setUserRole(request, User.Role.MASTER, email);
            return "redirect:/master";
        } else if (user.get().getRole().equals(User.Role.USER)) {
            CommandUtility.setUserRole(request, User.Role.USER, email);
            return "redirect:/user";
        }
        return "/login.jsp";
    }

}
