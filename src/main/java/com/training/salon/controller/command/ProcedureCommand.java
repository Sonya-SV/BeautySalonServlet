package com.training.salon.controller.command;

import com.training.salon.model.service.ProcedureService;

import javax.servlet.http.HttpServletRequest;

public class ProcedureCommand implements ICommand{

    private ProcedureService procedureService;

    public ProcedureCommand(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        return "/app/user/serviceSelection";
    }
}
