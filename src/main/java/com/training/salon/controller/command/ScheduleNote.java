package com.training.salon.controller.command;

import com.training.salon.controller.exception.BookException;
import com.training.salon.model.entity.Schedule;
import com.training.salon.model.service.ScheduleService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.training.salon.controller.command.ITextConstant.ALREADY_BOOKED;
import static com.training.salon.controller.command.ITextConstant.UNABLE_TO_CREATE_RECORD;

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

        Schedule schedule = (Schedule) request.getSession().getAttribute("schedule");
        schedule.setClientFirstName(firstName);
        schedule.setClientLastName(lastName);
        schedule.setDone(false);
        try {
            scheduleService.saveToSchedule(schedule);
            log.info("Note added to the master (" +schedule.getMaster().getUser().getFirstName() + ") Schedule");
        } catch (BookException e) {
            request.setAttribute("alreadyBooked", ALREADY_BOOKED);
            return "/WEB-INF/"+request.getSession().getAttribute("role").toString().toLowerCase()+"/order.jsp";
        } catch (java.lang.Exception e){
            request.setAttribute("errorOrder", UNABLE_TO_CREATE_RECORD);
            return "/WEB-INF/"+request.getSession().getAttribute("role").toString().toLowerCase()+"/order.jsp";
        }
        return "redirect:/successpage";
    }
}
