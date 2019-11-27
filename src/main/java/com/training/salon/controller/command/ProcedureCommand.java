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
        String categoryId = request.getParameter("categoryId");
        if (Optional.ofNullable(request.getParameter("categoryId")).isEmpty())
            return "redirect:/"+ request.getHeader("referer").replaceAll(".*/beauty-salon/","");
        request.setAttribute("procedures", procedureService.getAllProceduresByCategory(Long.valueOf(categoryId)));
        request.setAttribute("masters", masterService.getMastersByCategory(Long.valueOf(categoryId)));

        return "/WEB-INF/"+request.getSession().getAttribute("role").toString().toLowerCase()+"/procedures.jsp";
    }
}
