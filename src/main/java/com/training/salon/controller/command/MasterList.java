package com.training.salon.controller.command;

import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.User;
import com.training.salon.model.service.MasterService;
import com.training.salon.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class MasterList implements ICommand{

    private MasterService masterService;

    public MasterList(MasterService masterService) {
        this.masterService = masterService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        request.setAttribute("masters", masterService.getAllMasters());
        if(request.getSession().getAttribute("role").equals(User.Role.ADMIN))
            return "/WEB-INF/admin/masterlist.jsp";
        else if(request.getSession().getAttribute("role").equals(User.Role.MASTER))
            return "/WEB-INF/master/masterlist.jsp";
        return "/WEB-INF/user/masterlist.jsp";
    }
}
