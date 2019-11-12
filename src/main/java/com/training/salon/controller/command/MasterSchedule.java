package com.training.salon.controller.command;

import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.Schedule;
import com.training.salon.model.entity.User;
import com.training.salon.model.service.MasterService;
import com.training.salon.model.service.ScheduleService;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.mail.*;

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
        if(request.getSession().getAttribute("role").equals(User.Role.ADMIN))
             master= masterService.getById(Long.valueOf(request.getParameter("masterId")));
        else
            master = masterService.getMaster(((User) request.getSession().getAttribute("user")).getId());

        List<LocalDate> dates = Stream.iterate(LocalDate.now(), curr -> curr.plusDays(1))
                .limit(ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusDays(7)))
                .collect(Collectors.toList());
        request.setAttribute("dates", dates);

        if (master.isPresent()) {
            LocalTime start = master.get().getTimeStart();
            LocalTime end = master.get().getTimeEnd();
            System.out.println("master " + master.get().getUser().getFirstName() );
            List<Schedule> schedule = scheduleService.getScheduleForMaster(master.get().getId());
            List<LocalTime> workTime = Stream.iterate(start, curr -> curr.plusHours(1))
                    .limit(ChronoUnit.HOURS.between(start, end))
                    .collect(Collectors.toList());

            request.setAttribute("workTime", workTime);
            request.setAttribute("schedule", schedule);

        }
        if(request.getSession().getAttribute("role").equals(User.Role.ADMIN))
            return "/WEB-INF/admin/schedule.jsp";
        else
            return "/WEB-INF/master/schedule.jsp";
    }
}
