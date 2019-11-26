package com.training.salon.controller.command;

import com.training.salon.controller.exception.DiscrepancyException;
import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.Procedure;
import com.training.salon.model.entity.Schedule;
import com.training.salon.model.service.MasterService;
import com.training.salon.model.service.ProcedureService;
import com.training.salon.model.service.ScheduleService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.training.salon.controller.command.ITextConstant.PROCEDURE_ERROR;

public class ChooseProcedureCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(ChooseProcedureCommand.class);
    private ScheduleService scheduleService;
    private ProcedureService procedureService;
    private MasterService masterService;

    public ChooseProcedureCommand(ScheduleService scheduleService, ProcedureService procedureService, MasterService masterService) {
        this.scheduleService = scheduleService;
        this.procedureService = procedureService;
        this.masterService = masterService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long masterId = Long.valueOf(request.getParameter("masterId"));
        Long procedureId = Long.valueOf(request.getParameter("procedureId"));

        Optional<Master> master = masterService.getById(masterId);
        if (master.isEmpty())
            return "redirect:/" + request.getSession().getAttribute("role").toString().toLowerCase() + "/masterlist";


        Schedule schedule = new Schedule();
        schedule.setMaster(master.get());
        request.getSession().setAttribute("schedule", schedule);
        request.setAttribute("busySchedule", scheduleService.getScheduleForMaster(masterId));

        request.setAttribute("scheduleDate", Stream.iterate(LocalDate.now(), curr -> curr.plusDays(1))
                .limit(ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusDays(7)))
                .collect(Collectors.toList()));

        request.setAttribute("availableTime", Stream.iterate(master.get().getTimeStart(), curr -> curr.plusHours(1))
                .limit(ChronoUnit.HOURS.between(master.get().getTimeStart(), master.get().getTimeEnd()))
                .collect(Collectors.toList()));

        try {
            masterService.isProcedureAccordToMaster(masterId, procedureId);
        } catch (DiscrepancyException e) {
            request.setAttribute("discrepancy", "Master doesn't do such procedure");
            return "/WEB-INF/"+request.getSession().getAttribute("role").toString().toLowerCase() + "/booking.jsp";
        }
        procedureService.getProcedureById(procedureId).ifPresent(schedule::setProcedure);

        return "/WEB-INF/"+request.getSession().getAttribute("role").toString().toLowerCase()+"/booking.jsp";
    }
}
