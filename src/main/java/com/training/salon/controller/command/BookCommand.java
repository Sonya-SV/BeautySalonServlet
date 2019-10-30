package com.training.salon.controller.command;

import com.training.salon.controller.exception.BookException;
import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.Procedure;
import com.training.salon.model.entity.Schedule;
import com.training.salon.model.entity.User;
import com.training.salon.model.service.MasterService;
import com.training.salon.model.service.ProcedureService;
import com.training.salon.model.service.ScheduleService;

import javax.enterprise.inject.AmbiguousResolutionException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookCommand implements ICommand {

    private ScheduleService scheduleService;
    private ProcedureService procedureService;
    private MasterService masterService;

    public BookCommand(ScheduleService scheduleService, ProcedureService procedureService, MasterService masterService) {
        this.scheduleService = scheduleService;
        this.procedureService = procedureService;
        this.masterService = masterService;
    }



    @Override
    public String execute(HttpServletRequest request) {
        Long masterId = Long.valueOf(request.getParameter("masterId"));
//        List<Procedure> procedures = procedureService.getAllProceduresByMaster(masterId);
//        request.setAttribute("procedures", );


        request.setAttribute("procedures", procedureService.getAllProceduresByMaster(masterId));
        request.setAttribute("dateNow", LocalDate.now());
        request.setAttribute("maxDate", LocalDate.now().plusDays(7));

        //TODO rewrite if
        //TODO rewrite for available time


//        User user = (User) request.getSession().getAttribute("user");


        Optional<Master> master = masterService.getById(masterId);
        if(master.isPresent()) {
            LocalTime start = master.get().getTimeStart();
            LocalTime end = master.get().getTimeEnd();

            List<LocalTime> schedule = Stream.iterate(start, curr -> curr.plusHours(1))
                    .limit(ChronoUnit.HOURS.between(start, end))
                    .collect(Collectors.toList());

            request.setAttribute("schedule", schedule );
        }
        master.ifPresent(m->request.setAttribute("master", m));



        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");


        if (firstName == null || firstName.equals("") || lastName == null || lastName.equals("")) {
            return "/app/user/booking";
        }

        Long procedureId = Long.valueOf(request.getParameter("procedureId"));
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        LocalTime time = LocalTime.parse(request.getParameter("time"));
        try {
            scheduleService.saveToSchedule(time,date,  Long.valueOf(0), masterId,  procedureId, false, null);
            System.out.println("saved");
        } catch (BookException e) {
            System.out.println("already booked");
        }
        return "/app/user/";
    }
}
