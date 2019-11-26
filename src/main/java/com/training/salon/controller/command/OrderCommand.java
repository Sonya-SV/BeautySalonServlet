package com.training.salon.controller.command;

import com.training.salon.controller.exception.DiscrepancyException;
import com.training.salon.model.entity.Schedule;
import com.training.salon.model.entity.User;
import com.training.salon.model.service.MasterService;
import com.training.salon.model.service.ScheduleService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.training.salon.controller.command.ITextConstant.DAYS_iN_SCHEDULE;
import static com.training.salon.controller.command.ITextConstant.UNAVAILABLE_TIME;

public class OrderCommand implements ICommand {
    private MasterService masterService;

    public OrderCommand(MasterService masterService) {
        this.masterService = masterService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        LocalDate date = LocalDate.parse(request.getParameter("dateOrder"));
        LocalTime time = LocalTime.parse(request.getParameter("timeOrder"));
        User user = (User) request.getSession().getAttribute("user");
        Schedule scheduleNote = (Schedule) request.getSession().getAttribute("schedule");

        request.setAttribute("schedule", scheduleNote);
        request.setAttribute("user", user);

        try {
            masterService.checkTimeForMaster(scheduleNote.getMaster().getId(), time);
            if (date.isBefore(LocalDate.now()) || date.isAfter(LocalDate.now().plusDays(DAYS_iN_SCHEDULE)))
                throw new DiscrepancyException();
        } catch (DiscrepancyException e) {
            request.setAttribute("timeError", UNAVAILABLE_TIME);
            return "/WEB-INF/"+request.getSession().getAttribute("role").toString().toLowerCase()+"/order.jsp";
        }
        scheduleNote.setDate(date);
        scheduleNote.setTime(time);
        scheduleNote.setUser(user);
        return "/WEB-INF/"+request.getSession().getAttribute("role").toString().toLowerCase()+"/order.jsp";

    }
}
