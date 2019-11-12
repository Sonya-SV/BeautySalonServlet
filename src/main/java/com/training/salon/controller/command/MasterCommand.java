package com.training.salon.controller.command;

import com.training.salon.controller.exception.BookException;
import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.Procedure;
import com.training.salon.model.entity.Schedule;
import com.training.salon.model.entity.User;
import com.training.salon.model.service.CommentService;
import com.training.salon.model.service.MasterService;
import com.training.salon.model.service.ProcedureService;
import com.training.salon.model.service.ScheduleService;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Long masterId = Long.valueOf(request.getParameter("masterId"));
        Optional<Master> master = masterService.getById(masterId);

        request.setAttribute("comments", commentService.getAllByMaster(masterId));
        request.setAttribute("procedures", procedureService.getAllProceduresByMaster(masterId));
        master.ifPresent(m -> request.setAttribute("master", m));

        if (request.getSession().getAttribute("role").equals(User.Role.ADMIN))
            return "/WEB-INF/admin/master.jsp";
        else if (request.getSession().getAttribute("role").equals(User.Role.MASTER))
            return "/WEB-INF/master/master.jsp";
        else
            return "/WEB-INF/user/master.jsp";
    }
}
