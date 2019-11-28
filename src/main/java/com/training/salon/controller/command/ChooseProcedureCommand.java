package com.training.salon.controller.command;

import com.training.salon.controller.exception.DiscrepancyException;
import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.Schedule;
import com.training.salon.model.service.MasterService;
import com.training.salon.model.service.ProcedureService;
import com.training.salon.model.service.ScheduleService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.training.salon.controller.command.IConstant.DAYS_IN_SCHEDULE;

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
        String masterId = request.getParameter("masterId");
        String procedureId = request.getParameter("procedureId");

        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(Optional.ofNullable((String) request.getSession().getAttribute("lang")).orElse("en")));
        if (Optional.ofNullable(masterId).isEmpty())
            return "redirect:/" + request.getHeader("referer").replaceAll(".*/beauty-salon/", "");

        Optional<Master> master = masterService.getById(Long.valueOf(masterId));
        if (master.isEmpty())
            return "redirect:/" + request.getSession().getAttribute("role").toString().toLowerCase() + "/masterlist";

        Schedule schedule = new Schedule();
        schedule.setMaster(master.get());
        request.getSession().setAttribute("schedule", schedule);
        request.setAttribute("busySchedule", scheduleService.getScheduleForMaster(Long.valueOf(masterId)));

        request.setAttribute("scheduleDate", Stream.iterate(LocalDate.now(), curr -> curr.plusDays(1))
                .limit(ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusDays(DAYS_IN_SCHEDULE)))
                .collect(Collectors.toList()));

        request.setAttribute("availableTime", Stream.iterate(master.get().getTimeStart(), curr -> curr.plusHours(1))
                .limit(ChronoUnit.HOURS.between(master.get().getTimeStart(), master.get().getTimeEnd()))
                .collect(Collectors.toList()));

        try {
            masterService.isProcedureAccordToMaster(Long.valueOf(masterId), Long.valueOf(procedureId));
        } catch (DiscrepancyException e) {
            request.setAttribute("discrepancy", bundle.getString("procedure.error"));
            return "/WEB-INF/" + request.getSession().getAttribute("role").toString().toLowerCase() + "/booking.jsp";
        }
        procedureService.getProcedureById(Long.valueOf(procedureId)).ifPresent(schedule::setProcedure);
        return "/WEB-INF/" + request.getSession().getAttribute("role").toString().toLowerCase() + "/booking.jsp";
    }
}
