package com.training.salon.controller.command;

import com.training.salon.controller.exception.BookException;
import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.Procedure;
import com.training.salon.model.entity.Schedule;
import com.training.salon.model.entity.User;
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

public class MasterCommand implements ICommand{

    private ProcedureService procedureService;
    private MasterService masterService;
    public MasterCommand(ProcedureService procedureService, MasterService masterService) {
        this.procedureService = procedureService;
        this.masterService = masterService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        Long masterId = Long.valueOf(request.getParameter("masterId"));
        Optional<Master> master = masterService.getById(masterId);
        List<Procedure> procedures = procedureService.getAllProceduresByMaster(masterId);

        request.setAttribute("procedures", procedures);
        master.ifPresent(m->request.setAttribute("master", m));

        return "/WEB-INF/user/master.jsp";
    }
}
