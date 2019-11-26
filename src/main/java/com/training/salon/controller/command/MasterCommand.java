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

        Optional<String> locale = Optional.ofNullable((String) request.getSession().getAttribute("lang"));
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(locale.orElse("en")));
        if (Optional.ofNullable(request.getParameter("masterId")).isEmpty())
            return request.getHeader("referer");
        Long masterId = Long.valueOf(request.getParameter("masterId"));
        Optional<Master> master = masterService.getById(masterId);
        if (master.isEmpty())
            return "redirect:/" + request.getSession().getAttribute("role").toString().toLowerCase() + "/masterlist";

        request.setAttribute("comments", commentService.getAllByMaster(masterId));
        request.setAttribute("procedures", procedureService.getAllProceduresByMaster(masterId));
        master.ifPresent(m -> request.setAttribute("master", m));

        return "/WEB-INF/" + request.getSession().getAttribute("role").toString().toLowerCase() + "/master.jsp";
    }
}
