package com.training.salon.model.service;

import com.training.salon.model.dao.DaoFactory;
import com.training.salon.model.dao.ProcedureDao;
import com.training.salon.model.dao.UserDao;
import com.training.salon.model.entity.Category;
import com.training.salon.model.entity.Procedure;

import java.util.List;

public class ProcedureService {

    DaoFactory daoFactory = DaoFactory.getInstance();


    public List<Procedure> getAllProcedures(){
        try (ProcedureDao dao = daoFactory.createProcedureDao()) {
            return dao.findAll();
        }
    }

    public List<Procedure> getAllProceduresByCategory(Category category){
        try (ProcedureDao dao = daoFactory.createProcedureDao()) {
            return dao.findAllByCategory(category);
        }
    }
}
