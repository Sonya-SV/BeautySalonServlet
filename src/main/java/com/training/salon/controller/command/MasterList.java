package com.training.salon.controller.command;

import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.User;
import com.training.salon.model.service.MasterService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MasterList implements ICommand{

    private MasterService masterService;

    public MasterList(MasterService masterService) {
        this.masterService = masterService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        List<Master> masters = masterService.getAllMasters();
        int page=1;
        int rowsPerPage=8;
        if (request.getParameter("page") != null) page = Integer.parseInt(request.getParameter("page"));
        int noOfPages = (int) (Math.ceil((double)masters.size() / rowsPerPage));

        request.setAttribute("masters",
                masters.subList((page-1)*rowsPerPage,
                        Math.min((page-1)*rowsPerPage+ rowsPerPage,
                                masters.size())));

        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        if(request.getSession().getAttribute("role").equals(User.Role.ADMIN))
            return "/WEB-INF/admin/masterlist.jsp";
        else if(request.getSession().getAttribute("role").equals(User.Role.MASTER))
            return "/WEB-INF/master/masterlist.jsp";
        return "/WEB-INF/user/masterlist.jsp";
    }
}
