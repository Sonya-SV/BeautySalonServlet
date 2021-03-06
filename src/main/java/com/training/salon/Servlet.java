package com.training.salon;

import com.training.salon.controller.command.Exception;
import com.training.salon.controller.command.*;
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

        commands.put("", new AccessCommand());
        commands.put("user", new UserRole());
        commands.put("master", new MasterRole());
        commands.put("admin", new AdminRole());
        commands.put("logout", new Logout());
        commands.put("login", new Login(new UserService()));
        commands.put("registration", new Registration(new UserService()));
        commands.put("exception", new Exception());
        commands.put("successpage", new SuccessPageCommand());
        commands.put("admin/userlist", new UserList(new UserService()));
        commands.put("user/masterlist", new MasterList(new MasterService()));
        commands.put("master/masterlist", new MasterList(new MasterService()));
        commands.put("admin/masterlist", new MasterList(new MasterService()));
        commands.put("user/procedures", new ProcedureCommand(new ProcedureService(), new MasterService()));
        commands.put("master/procedures", new ProcedureCommand(new ProcedureService(), new MasterService()));
        commands.put("admin/procedures", new ProcedureCommand(new ProcedureService(), new MasterService()));
        commands.put("user/categorylist", new CategoryCommand(new CategoryService()));
        commands.put("master/categorylist", new CategoryCommand(new CategoryService()));
        commands.put("admin/categorylist", new CategoryCommand(new CategoryService()));
        commands.put("user/master", new MasterCommand(new ProcedureService(), new MasterService(), new CommentService()));
        commands.put("master/master", new MasterCommand(new ProcedureService(), new MasterService(), new CommentService()));
        commands.put("admin/master", new MasterCommand(new ProcedureService(), new MasterService(), new CommentService()));
        commands.put("user/booking", new ChooseProcedureCommand( new ScheduleService(),new ProcedureService(), new MasterService()));
        commands.put("master/booking", new ChooseProcedureCommand( new ScheduleService(),new ProcedureService(), new MasterService()));
        commands.put("admin/booking", new ChooseProcedureCommand( new ScheduleService(),new ProcedureService(), new MasterService()));
        commands.put("master/schedule", new MasterSchedule(new MasterService(), new ScheduleService()));
        commands.put("admin/schedule", new MasterSchedule(new MasterService(), new ScheduleService()));
        commands.put("user/comment", new SendComment(new CommentService(), new MasterService()));
        commands.put("master/comment", new SendComment(new CommentService(), new MasterService()));
        commands.put("admin/comment", new SendComment(new CommentService(), new MasterService()));
        commands.put("master/sendemail", new SendEmail(new ScheduleService(), new MailSender()));
        commands.put("admin/sendemail", new SendEmail(new ScheduleService(), new MailSender()));
        commands.put("user/save", new ScheduleNote(new ScheduleService()));
        commands.put("master/save", new ScheduleNote(new ScheduleService()));
        commands.put("admin/save", new ScheduleNote(new ScheduleService()));
        commands.put("user/profile", new Profile(new UserService()));
        commands.put("master/profile", new Profile(new UserService()));
        commands.put("admin/profile", new Profile(new UserService()));
        commands.put("user/order", new OrderCommand(new MasterService()));
        commands.put("master/order", new OrderCommand(new MasterService()));
        commands.put("admin/order", new OrderCommand(new MasterService()));

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        path = path.replaceAll(".*/beauty-salon/", "");
        ICommand command = commands.getOrDefault(path, (r) -> "");
        String page = command.execute(request);

        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", "/beauty-salon"));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
