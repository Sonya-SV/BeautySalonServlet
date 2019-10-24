package com.training.salon.model.service;

import com.training.salon.model.dao.CategoryDao;
import com.training.salon.model.dao.DaoFactory;
import com.training.salon.model.dao.ProcedureDao;
import com.training.salon.model.entity.Category;
import com.training.salon.model.entity.Procedure;

import java.util.List;

public class CategoryService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Category> getAllCategories(){
        try (CategoryDao dao = daoFactory.createCategoryDao()) {
            return dao.findAll();
        }
    }
}
