package com.training.salon.controller.command;

import com.training.salon.controller.exception.BookException;
import com.training.salon.model.entity.Schedule;
import com.training.salon.model.service.ScheduleService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class ScheduleNote implements ICommand {
    private  static final Logger log = LogManager.getLogger(Registration.class);
    private ScheduleService scheduleService;

    public ScheduleNote(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(Optional.ofNullable( (String) request.getSession().getAttribute("lang")).orElse("en")));
        Schedule schedule = (Schedule) request.getSession().getAttribute("schedule");
        schedule.setClientFirstName(firstName);
        schedule.setClientLastName(lastName);
        schedule.setDone(false);
        try {
            scheduleService.saveToSchedule(schedule);
            log.info("Note added to the master (" +schedule.getMaster().getUser().getFirstName() + ") Schedule");
        } catch (BookException e) {
            request.setAttribute("alreadyBooked", bundle.getString("already.booked"));
            return "/WEB-INF/"+request.getSession().getAttribute("role").toString().toLowerCase()+"/order.jsp";
        } catch (java.lang.Exception e){
            request.setAttribute("errorOrder", bundle.getString("unable.to.create.record"));
            return "/WEB-INF/"+request.getSession().getAttribute("role").toString().toLowerCase()+"/order.jsp";
        }
        return "redirect:/successpage";
    }
}
