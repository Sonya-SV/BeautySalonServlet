package com.training.salon.controller.command;


import com.training.salon.model.entity.User;
import com.training.salon.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static java.util.Objects.nonNull;

public class Login implements ICommand {
//    private static final Logger logger = LogManager.getLogger(Login.class);

    public static final String USER_ERROR = "Invalid login or password";

    private UserService userService;

    public Login(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (nonNull(request.getSession().getAttribute("user")))
            return "/index.jsp";

        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        if (email == null || email.equals("") || pass == null || pass.equals("")) {
            return "/login.jsp";
        }

        Optional<User> user = userService.login(email, pass);
        if (user.isPresent()) {
            request.getSession().setAttribute("user", user.get());
            if (CommandUtility.checkUserIsLogged(request, email)) {
                return "/WEB-INF/error.jsp";
                //TODO rewrite
            }
            if (user.get().getRole().equals(User.Role.ADMIN)) {
                CommandUtility.setUserRole(request, User.Role.ADMIN, email);
                return "/index.jsp";
            } else if (user.get().getRole().equals(User.Role.MASTER)) {
                CommandUtility.setUserRole(request, User.Role.MASTER, email);
                return "/index.jsp";
            } else if (user.get().getRole().equals(User.Role.USER)) {
                CommandUtility.setUserRole(request, User.Role.USER, email);
                return "/index.jsp";
            } else {
                CommandUtility.setUserRole(request, User.Role.GUEST, email);
                return "/login.jsp";
            }
        }else {
            request.setAttribute("userError", USER_ERROR);
        }
        return "/login.jsp";
    }
}
