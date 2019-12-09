package com.training.salon.controller.command;

import com.training.salon.controller.exception.DiscrepancyException;
import com.training.salon.model.entity.Schedule;
import com.training.salon.model.entity.User;
import com.training.salon.model.service.MasterService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.training.salon.controller.command.IConstant.DAYS_IN_SCHEDULE;

public class OrderCommand implements ICommand {

    private MasterService masterService;

    public OrderCommand(MasterService masterService) {
        this.masterService = masterService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String dateOrder = request.getParameter("dateOrder");
        String timeOrder = request.getParameter("timeOrder");
        User user = (User) request.getSession().getAttribute("user");
        Schedule scheduleNote = (Schedule) request.getSession().getAttribute("schedule");
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(Optional.ofNullable( (String) request.getSession().getAttribute("lang")).orElse("en")));
        if(Optional.ofNullable(timeOrder).isEmpty())
            return  "redirect:/"+ request.getHeader("referer").replaceAll(".*/beauty-salon/","");

        request.setAttribute("schedule", scheduleNote);
        request.setAttribute("user", user);
        LocalDate date = LocalDate.parse(dateOrder);
        try {
            masterService.checkTimeForMaster(scheduleNote.getMaster().getId(), LocalTime.parse(timeOrder));
            if (date.isBefore(LocalDate.now()) || date.isAfter(LocalDate.now().plusDays(DAYS_IN_SCHEDULE)))
                throw new DiscrepancyException();
        } catch (DiscrepancyException e) {
            request.setAttribute("timeError", bundle.getString("unavailable.time"));
            return "/WEB-INF/"+request.getSession().getAttribute("role").toString().toLowerCase()+"/order.jsp";
        }
        scheduleNote.setDate(date);
        scheduleNote.setTime(LocalTime.parse(timeOrder));
        scheduleNote.setUser(user);
        return "/WEB-INF/"+request.getSession().getAttribute("role").toString().toLowerCase()+"/order.jsp";

    }
}
