package com.training.salon.controller.command;

import com.training.salon.controller.exception.BookException;
import com.training.salon.model.entity.Schedule;
import com.training.salon.model.entity.User;
import com.training.salon.model.service.ScheduleService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleNote implements ICommand {
    private ScheduleService scheduleService;

    public ScheduleNote(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        LocalTime time = LocalTime.parse(request.getParameter("time"));


        Schedule schedule = (Schedule) request.getSession().getAttribute("schedule");
        schedule.setClientFirstName(firstName);
        schedule.setClientLastName(lastName);
        schedule.setDate(date);
        schedule.setTime(time);
        schedule.setDone(false);
//        request.setAttribute("schedule", schedule);
//        request.setAttribute("user", user);
        try {
            scheduleService.saveToSchedule(schedule);
            System.out.println("saved");
//            return "redirect:/user/booking";
        } catch (BookException e) {
            System.out.println("already booked");
            request.setAttribute("alreadyBooked", "Already booked");
            return "/WEB-INF/user/order.jsp";
        }
        return "/WEB-INF/user/successpage.jsp";
    }
}
