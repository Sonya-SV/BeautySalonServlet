package com.training.salon.controller.command;

import com.training.salon.controller.exception.BookException;
import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.Procedure;
import com.training.salon.model.entity.Schedule;
import com.training.salon.model.entity.User;
import com.training.salon.model.service.MasterService;
import com.training.salon.model.service.ProcedureService;
import com.training.salon.model.service.ScheduleService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChooseProcedureCommand implements ICommand {

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

        request.setAttribute("busySchedule", scheduleService.getScheduleForMaster(masterId));

        Optional<Procedure> procedure = procedureService.getProcedureById(procedureId);
//        procedure.ifPresent(p->request.setAttribute("procedure",p));


        Optional<Master> master = masterService.getById(masterId);
        if (master.isPresent() && procedure.isPresent()){
            Schedule schedule = new Schedule();
            schedule.setMaster(master.get());
            schedule.setProcedure(procedure.get());
            request.getSession().setAttribute("schedule", schedule);

            LocalTime start = master.get().getTimeStart();
            LocalTime end = master.get().getTimeEnd();
            List<LocalTime> scheduleTime = Stream.iterate(start, curr -> curr.plusHours(1))
                    .limit(ChronoUnit.HOURS.between(start, end))
                    .collect(Collectors.toList());
            request.setAttribute("availableTime", scheduleTime );
//            request.setAttribute("master", master.get());
        }

        request.setAttribute("scheduleDate", Stream.iterate(LocalDate.now(), curr -> curr.plusDays(1))
                .limit(ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusDays(7)))
                .collect(Collectors.toList()));

        return "/WEB-INF/user/booking.jsp";
    }
}
