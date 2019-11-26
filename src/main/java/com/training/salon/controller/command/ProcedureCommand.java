package com.training.salon.controller.command;

import com.training.salon.model.service.MasterService;
import com.training.salon.model.service.ProcedureService;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProcedureCommand implements ICommand{

    private ProcedureService procedureService;
    private MasterService masterService;

    public ProcedureCommand(ProcedureService procedureService, MasterService masterService) {
        this.procedureService = procedureService;
        this.masterService = masterService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long categoryId = Long.valueOf(request.getParameter("categoryId"));
        Optional<String> locale = Optional.ofNullable((String) request.getSession().getAttribute("lang"));
        ResourceBundle.getBundle("messages", new Locale(locale.orElse("en")));
        request.setAttribute("procedures", procedureService.getAllProceduresByCategory(categoryId));
        request.setAttribute("masters", masterService.getMastersByCategory(categoryId));

        return "/WEB-INF/"+request.getSession().getAttribute("role").toString().toLowerCase()+"/procedures.jsp";
    }
}
