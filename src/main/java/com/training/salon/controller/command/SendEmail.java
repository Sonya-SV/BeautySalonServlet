package com.training.salon.controller.command;

import com.training.salon.model.entity.Schedule;
import com.training.salon.model.entity.User;
import com.training.salon.model.service.MailSender;
import com.training.salon.model.service.ScheduleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SendEmail implements ICommand {

    private ScheduleService scheduleService;
    private MailSender mailSender;

    public SendEmail(ScheduleService scheduleService, MailSender mailSender) {
        this.scheduleService = scheduleService;
        this.mailSender = mailSender;
    }


    @Override
    public String execute(HttpServletRequest request) {

        scheduleService.makeNoteDone(Long.valueOf(request.getParameter("done")));
        Optional<Schedule> scheduleNote = scheduleService.getScheduleNote(Long.valueOf(request.getParameter("done")));
        scheduleNote.ifPresent(schedule -> mailSender.send(schedule.getUser().getEmail(), schedule.getMaster().getId()));

        if(request.getSession().getAttribute("role").equals(User.Role.ADMIN))
            return "redirect:/admin/schedule?masterId="+scheduleNote.get().getMaster().getId();
        else return "redirect:/master/schedule";
    }
}
