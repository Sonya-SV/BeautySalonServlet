package com.training.salon.controller.command;

import com.training.salon.model.service.CommentService;

import javax.servlet.http.HttpServletRequest;

public class CommentsCommand implements ICommand {

    private CommentService commentService;

    public CommentsCommand(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        request.setAttribute("comments", commentService.getAllComments());
        return "redirect:/WEB-INF/admin/comments.jsp";
    }
}
