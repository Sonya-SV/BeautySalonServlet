package com.training.salon.controller.command;

import com.training.salon.model.entity.Category;
import com.training.salon.model.service.CategoryService;
import com.training.salon.model.service.ProcedureService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CategoryCommand implements ICommand {
    private CategoryService categoryService;

    public CategoryCommand(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        request.setAttribute("categories", categoryService.getAllCategories());
        return "/app/user/categoryList";
    }
}
