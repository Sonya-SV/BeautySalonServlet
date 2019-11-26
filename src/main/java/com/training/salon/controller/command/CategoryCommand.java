package com.training.salon.controller.command;

import com.training.salon.model.service.CategoryService;

import javax.servlet.http.HttpServletRequest;

public class CategoryCommand implements ICommand {
    private CategoryService categoryService;

    public CategoryCommand(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("categories", categoryService.getAllCategories());
        return "/WEB-INF/"+request.getSession().getAttribute("role").toString().toLowerCase()+"/categorylist.jsp";
    }
}
