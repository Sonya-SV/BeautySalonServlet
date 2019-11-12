package com.training.salon.controller.command;


import com.training.salon.model.entity.User;
import com.training.salon.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class Profile implements ICommand {

//    private static final Logger log = LogManager.getLogger(Profile.class);
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
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        User user = (User)request.getSession().getAttribute("user");
        if (request.getParameter("password") == null || request.getParameter("password2") == null)
            return "/WEB-INF/" + user.getRole().name().toLowerCase()+ "/"+ user.getRole().name().toLowerCase()+"profile.jsp";


        if (password.equals(password2)) {
            try {
                userService.update(firstName, lastName, password, user);
                request.setAttribute("successSave", SUCCESS_SAVE);
            } catch (SQLException e) {
                //TODO
//                log.info("cant save new password");
            }
        }
        else {
            request.setAttribute("passwordErrorDiffer", PASSWORD_DIFFERENT);
        }
        if(request.getSession().getAttribute("role").equals(User.Role.ADMIN))
            return "/WEB-INF/admin/adminprofile.jsp";
        else if(request.getSession().getAttribute("role").equals(User.Role.MASTER))
            return "/WEB-INF/master/masterprofile.jsp";
        else return "/WEB-INF/user/userprofile.jsp";
    }
}
