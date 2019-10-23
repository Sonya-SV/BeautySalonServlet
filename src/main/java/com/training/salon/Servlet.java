package com.training.salon;

import com.training.salon.controller.command.*;
import com.training.salon.controller.command.Exception;
import com.training.salon.model.service.ProcedureService;
import com.training.salon.model.service.UserService;

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

//        commands.put("logout", new LogOut());
        commands.put("login", new Login(new UserService()));
        commands.put("registration", new Registration(new UserService()));
        commands.put("exception", new Exception());
        commands.put("admin/userList", new UserList(new UserService()));
        commands.put("user/serviceSelection", new ProcedureCommand(new ProcedureService()));
//        commands.put("user/trainList", new TrainCommand(new TrainService()));
//        commands.put("user/order", new OrderCommand(new TrainService()));
//        commands.put("user/cart", new CartCommand( new TicketService()));
//        commands.put("user/history", new HistoryCommand(new TicketService()));
//        commands.put("user/account", new AccountCommand(new UserService()));
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
        path = path.replaceAll(".*/app/", "");
        System.out.println("path= " + path);


        ICommand command = commands.getOrDefault(path,
                (r) -> "/index.jsp)");
        String page = command.execute(request);
        if (path.contains("admin")) {
            request.getRequestDispatcher("/WEB-INF/admin/userlist.jsp").forward(request, response);
            return;
        }
        if (path.contains("user")) {

            path = path.replace("user", "");
            path = path.toLowerCase();
            System.out.println(path);
            request.getRequestDispatcher("/WEB-INF/user/" + path + ".jsp").forward(request, response);
            return;
        }

        if (page.contains("redirect:")) {
            page = page.replace("redirect:", "/api");
            response.sendRedirect(page);
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }

    }
}
