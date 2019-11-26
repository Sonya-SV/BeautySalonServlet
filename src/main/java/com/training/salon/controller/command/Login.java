package com.training.salon.controller.command;


import com.training.salon.model.entity.User;
import com.training.salon.model.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.training.salon.controller.command.ITextConstant.USER_ERROR;
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
        if (isNull(email)) return "/login.jsp";
        Optional<User> user = userService.login(email, pass);
        if (user.isEmpty()) {
            log.info("Invalid attempt of user email: '" + email + "'");
            request.setAttribute("userError", USER_ERROR);
            return "/login.jsp";
        }
        request.getSession().setAttribute("user", user.get());
        log.info("User \"" + email + "\" logged successfully");
        if (CommandUtility.checkUserIsLogged(request, email)) {
            return "/WEB-INF/error.jsp";
        }
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
