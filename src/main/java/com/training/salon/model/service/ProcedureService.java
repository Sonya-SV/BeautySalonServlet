package com.training.salon.model.service;

import com.training.salon.model.dao.DaoFactory;
import com.training.salon.model.dao.MasterDao;
import com.training.salon.model.dao.ProcedureDao;
import com.training.salon.model.dao.UserDao;
import com.training.salon.model.entity.Category;
import com.training.salon.model.entity.Procedure;

import java.util.List;
import java.util.Optional;

public class ProcedureService {

    DaoFactory daoFactory = DaoFactory.getInstance();


    public List<Procedure> getAllProcedures(){
        try (ProcedureDao dao = daoFactory.createProcedureDao()) {
            return dao.findAll();
        }
    }

    public List<Procedure> getAllProceduresByCategory(Long categoryId){
        try (ProcedureDao dao = daoFactory.createProcedureDao()) {
            return dao.findAllByCategory(categoryId);
        }
    }
    public Optional<Procedure> getProcedureById(Long procedureId){
        try (ProcedureDao dao = daoFactory.createProcedureDao()) {
            return dao.findById(procedureId);
        }
    }
    public List<Procedure> getAllProceduresByMaster(Long masterId){
        try (ProcedureDao dao = daoFactory.createProcedureDao()) {
            return dao.findAllProceduresByMaster(masterId);
        }
    }
}
