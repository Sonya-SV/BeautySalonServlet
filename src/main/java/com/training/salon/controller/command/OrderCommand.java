package com.training.salon.controller.command;

import com.training.salon.controller.exception.BookException;
import com.training.salon.model.entity.Schedule;
import com.training.salon.model.entity.User;
import com.training.salon.model.service.ScheduleService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OrderCommand implements ICommand {
    private ScheduleService scheduleService;

    public OrderCommand(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        LocalDate date = LocalDate.parse(request.getParameter("dateOrder"));
        LocalTime time = LocalTime.parse(request.getParameter("time"));
        User user = (User) request.getSession().getAttribute("user");

        Schedule scheduleNote = (Schedule) request.getSession().getAttribute("schedule");
        scheduleNote.setDate(date);
        scheduleNote.setTime(time);
        scheduleNote.setUser(user);
        request.setAttribute("schedule", scheduleNote);
        request.setAttribute("user", user);

//        request.setAttribute("busySchedule", scheduleService.getScheduleForMaster(schedule.getMaster().getId()));



        LocalTime start = scheduleNote.getMaster().getTimeStart();
        LocalTime end = scheduleNote.getMaster().getTimeEnd();
        List<LocalTime> scheduleTime = Stream.iterate(start, curr -> curr.plusHours(1))
                .limit(ChronoUnit.HOURS.between(start, end))
                .collect(Collectors.toList());

        List<LocalDate> localDates = Stream.iterate(LocalDate.now(), curr -> curr.plusDays(1))
                .limit(ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusDays(7)))
                .collect(Collectors.toList());

        request.setAttribute("dates", localDates);
        Map<LocalDate, List<LocalTime>> sched = new TreeMap<>();

        //TODO rewrite for
        for (LocalDate ld : localDates)
            sched.put(ld, scheduleTime);

        List<Schedule> busy = scheduleService.getScheduleForMaster(scheduleNote.getMaster().getId());
//        System.out.println(busy);
//        for (Schedule busySchedule : busy)
//            for (LocalTime schedTime : busy)
//            sched.put(busySchedule.getDate(), sched.get(busySchedule.getDate())
//                    .stream()
//                    .filter(x->!x.equals(busySchedule.getTime()))
//                    .collect(Collectors.toList()));
//
//        for (LocalDate ld : sched.keySet())
//            System.out.println(ld + " " + sched.get(ld));
        List<String> list = new ArrayList<>();
        list.add("11:00, 12:00, 13:00, 14:00");
        list.add("12:00, 13:00, 14:00");
        list.add("11:00, 12:00, 13:00, 14:00, 15:00");
        list.add("14:00, 15:00");
        list.add("14:00");

            request.setAttribute("availableTime",  list);

        return "/WEB-INF/user/order.jsp";

    }
}
