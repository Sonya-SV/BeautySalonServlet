package com.training.salon.controller.command;

import com.training.salon.model.entity.Procedure;
import com.training.salon.model.service.ProcedureService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ProcedureCommand implements ICommand{

    private ProcedureService procedureService;

    public ProcedureCommand(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        Long categoryId = Long.valueOf(request.getParameter("categoryId"));
        request.setAttribute("procedures", procedureService.getAllProceduresByCategory(categoryId));
        return "/app/user/procedures";
    }
}
