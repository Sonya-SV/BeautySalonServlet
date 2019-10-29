package com.training.salon.controller.command;

import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.Procedure;
import com.training.salon.model.service.MasterService;
import com.training.salon.model.service.ProcedureService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class MasterCommand implements ICommand{

    private ProcedureService procedureService;
    private MasterService masterService;

    public MasterCommand(ProcedureService procedureService, MasterService masterService) {
        this.procedureService = procedureService;
        this.masterService = masterService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        Long masterId = Long.valueOf(request.getParameter("masterId"));
        Optional<Master> master = masterService.getById(masterId);
        List<Procedure> procedures = procedureService.getAllProceduresByMaster(masterId);

        master.ifPresent(m->request.setAttribute("master", m));
        request.setAttribute("procedures", procedures);
        return "/app/user/master";
    }
}
