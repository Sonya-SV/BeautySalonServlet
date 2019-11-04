package com.training.salon.controller.command;

import com.training.salon.model.entity.Comment;
import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.Schedule;
import com.training.salon.model.entity.User;
import com.training.salon.model.service.CommentService;
import com.training.salon.model.service.MasterService;
import com.training.salon.model.service.ScheduleService;

import javax.servlet.http.HttpServletRequest;

public class SendComment implements ICommand {

    private CommentService commentService;

    public SendComment(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long masterId = Long.valueOf(request.getParameter("masterId"));
        String comment = request.getParameter("comment");
        User user = (User) request.getSession().getAttribute("user");

        System.out.println("i m here");
        commentService.createComment(comment,  masterId, user.getId());
        System.out.println("sent");
        request.setAttribute("successSend", "Thank you for your comment");
        return "/app/user/master";
    }
}
