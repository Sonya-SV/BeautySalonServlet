package com.training.salon;

import com.training.salon.controller.command.*;
import com.training.salon.controller.command.Exception;
import com.training.salon.model.service.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {
    private Map<String, ICommand> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {

        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("logout", new Logout());
        commands.put("login", new Login(new UserService()));
        commands.put("registration", new Registration(new UserService()));
        commands.put("exception", new Exception());
        commands.put("admin/userList", new UserList(new UserService()));
        commands.put("user/masterList", new MasterList(new MasterService()));
        commands.put("user/procedures", new ProcedureCommand(new ProcedureService()));
        commands.put("user/categoryList", new CategoryCommand(new CategoryService()));
        commands.put("user/master", new MasterCommand(new ProcedureService(), new MasterService()));
        commands.put("user/booking", new BookCommand( new ScheduleService(),new ProcedureService(), new MasterService()));
        commands.put("master/schedule", new MasterSchedule(new MasterService(), new ScheduleService()));
        commands.put("user/comment", new SendComment(new CommentService()));
        commands.put("admin/comments", new CommentsCommand(new CommentService()));
//        commands.put("user/profile", new ProfileCommand(new UserService()));
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        System.out.println(path);
        path = path.replaceAll(".*/beauty-salon/", "");

        ICommand command = commands.getOrDefault(path, (r) -> "/beauty-salon/index.jsp)");
        String page = command.execute(request);

        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", "/beauty-salon"));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
