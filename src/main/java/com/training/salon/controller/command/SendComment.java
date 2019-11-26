package com.training.salon.controller.command;

import com.training.salon.model.entity.User;
import com.training.salon.model.service.CommentService;
import com.training.salon.model.service.MasterService;
import com.training.salon.model.service.ProcedureService;

import javax.servlet.http.HttpServletRequest;

import static com.training.salon.controller.command.ITextConstant.*;

public class SendComment implements ICommand {

    private CommentService commentService;
    private MasterService masterService;

    public SendComment(CommentService commentService, MasterService masterService) {
        this.commentService = commentService;
        this.masterService = masterService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long masterId = Long.valueOf(request.getParameter("masterId"));
        String comment = request.getParameter("comment");
        User user = (User) request.getSession().getAttribute("user");

        masterService.getById(masterId).ifPresent(m->request.setAttribute("master", m));
        commentService.createComment(comment,  masterId, user.getId());
        request.setAttribute("successSend", SUCCESS_SEND);

        return "redirect:/user/master?masterId="+masterId;
    }
}
