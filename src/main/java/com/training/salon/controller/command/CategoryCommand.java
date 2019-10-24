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
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        System.out.println(categories);
        return "/app/user/categoryList";
    }
}
