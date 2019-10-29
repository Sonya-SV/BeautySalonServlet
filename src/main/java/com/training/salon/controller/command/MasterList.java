package com.training.salon.controller.command;

import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.User;
import com.training.salon.model.service.MasterService;
import com.training.salon.model.service.UserService;

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


        String contentType = request.getServletContext().getMimeType(String.valueOf(masters.get(0).getPhoto()));
        request.setAttribute("masters", masters);
        request.setAttribute("photo", contentType);

        System.out.println(masters + "masters");
        return "/app/user/masterList";
    }
}
