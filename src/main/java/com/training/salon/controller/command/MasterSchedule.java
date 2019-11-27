package com.training.salon.controller.command;

import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.User;
import com.training.salon.model.service.MasterService;
import com.training.salon.model.service.ScheduleService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MasterSchedule implements ICommand {
    private MasterService masterService;
    private ScheduleService scheduleService;

    public MasterSchedule(MasterService masterService, ScheduleService scheduleService) {
        this.masterService = masterService;
        this.scheduleService = scheduleService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Optional<Master> master;
        if (request.getSession().getAttribute("role").equals(User.Role.ADMIN)) {
            master = masterService.getById(Long.valueOf(request.getParameter("masterId")));
        } else
            master = masterService.getMaster(((User) request.getSession().getAttribute("user")).getId());
        if (master.isEmpty()) return "redirect:/";

        request.setAttribute("schedule", scheduleService.getScheduleForMaster(master.get().getId()));
        request.setAttribute("dates", Stream.iterate(LocalDate.now(), curr -> curr.plusDays(1))
                .limit(ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusDays(7)))
                .collect(Collectors.toList()));

        request.setAttribute("workTime", Stream.iterate(master.get().getTimeStart(), curr -> curr.plusHours(1))
                .limit(ChronoUnit.HOURS.between(master.get().getTimeStart(), master.get().getTimeEnd()))
                .collect(Collectors.toList()));

        if (request.getSession().getAttribute("role").equals(User.Role.ADMIN))
            return "/WEB-INF/admin/schedule.jsp";
        else
            return "/WEB-INF/master/schedule.jsp";
    }
}
