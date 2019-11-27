package com.training.salon.controller.command;

import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.User;
import com.training.salon.model.service.CommentService;
import com.training.salon.model.service.MasterService;
import com.training.salon.model.service.ProcedureService;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class MasterCommand implements ICommand {

    private ProcedureService procedureService;
    private MasterService masterService;
    private CommentService commentService;

    public MasterCommand(ProcedureService procedureService, MasterService masterService, CommentService commentService) {
        this.procedureService = procedureService;
        this.masterService = masterService;
        this.commentService = commentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String masterId = request.getParameter("masterId");
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(Optional.ofNullable( (String) request.getSession().getAttribute("lang")).orElse("en")));
        if(Optional.ofNullable(request.getParameter("success")).isPresent())
            request.setAttribute("successSend", bundle.getString("saved.comment"));
        if (Optional.ofNullable(request.getParameter("masterId")).isEmpty())
            return "redirect:/"+ request.getHeader("referer").replaceAll(".*/beauty-salon/","");

        Optional<Master> master = masterService.getById(Long.valueOf(masterId));
        if (master.isEmpty())
            return "redirect:/" + request.getSession().getAttribute("role").toString().toLowerCase() + "/masterlist";

        if(request.getSession().getAttribute("role").equals(User.Role.ADMIN))
            request.setAttribute("comments", commentService.getAllByMaster(Long.valueOf(masterId)));
        request.setAttribute("procedures", procedureService.getAllProceduresByMaster(Long.valueOf(masterId)));
        master.ifPresent(m -> request.setAttribute("master", m));

        return "/WEB-INF/" + request.getSession().getAttribute("role").toString().toLowerCase() + "/master.jsp";
    }
}
