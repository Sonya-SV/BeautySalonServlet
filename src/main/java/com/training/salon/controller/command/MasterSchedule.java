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
//        Long masterId = Long.valueOf(request.getParameter("masterId"));
        List<LocalDate> dates = Stream.iterate(LocalDate.now(), curr -> curr.plusDays(1))
                .limit(ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusDays(7)))
                .collect(Collectors.toList());
        request.setAttribute("dates", dates);

        Optional<Master> master = masterService.getById((long) 2);

        System.out.println("master " + master );
        if (master.isPresent()) {
            LocalTime start = master.get().getTimeStart();
            LocalTime end = master.get().getTimeEnd();

            List<LocalTime> workTime = Stream.iterate(start, curr -> curr.plusHours(1))
                    .limit(ChronoUnit.HOURS.between(start, end))
                    .collect(Collectors.toList());

            request.setAttribute("workTime", workTime);
        }

            List<Schedule> schedule = scheduleService.getScheduleForMaster((long) 2);
            request.setAttribute("schedule", schedule);


        if(request.getParameter("done") == null || request.getParameter("done").equals("")){
            return "/app/master/schedule";

        }

        String to="";
        Optional<Schedule> scheduleNote = scheduleService.getScheduleNote(Long.valueOf(request.getParameter("done")));
        if(scheduleNote.isPresent())
        {
            to = scheduleNote.get().getUser().getEmail();
        }
        final String username = "kate.nilson123@gmail.com";
        final String password = "kate123kate";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Leave Comment");
            message.setText("http://localhost:8888/api");
            Transport.send(message);
            System.out.println("message sent");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        scheduleService.makeNoteDone(Long.valueOf(request.getParameter("done")));
        return "/app/master/schedule";
    }
}
