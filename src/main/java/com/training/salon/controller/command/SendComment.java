package com.training.salon.controller.command;

import com.training.salon.model.entity.Comment;
import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.User;
import com.training.salon.model.service.CommentService;
import com.training.salon.model.service.MasterService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;


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
        Optional<Master> master = masterService.getById(masterId);
        master.ifPresent(m->request.setAttribute("master", m));
        commentService.createComment(Comment.builder()
                .user(user)
                .master(master.orElse(null))
                .comment(comment)
                .dateTime(LocalDateTime.now())
                .build());

        return "redirect:/user/master?masterId="+masterId+"&success";
    }
}
