package com.training.salon.model.service;

import com.training.salon.controller.exception.DiscrepancyException;
import com.training.salon.model.dao.DaoFactory;
import com.training.salon.model.dao.MasterDao;
import com.training.salon.model.dao.UserDao;
import com.training.salon.model.entity.Category;
import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.Procedure;
import com.training.salon.model.entity.User;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class MasterService {
      DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Master> getAllMasters(){
        try (MasterDao dao = daoFactory.createMasterDao()) {
            return dao.findAll();
        }
    }

    public Optional<Master> getById(Long masterId){
        try (MasterDao dao = daoFactory.createMasterDao()) {
            return dao.findById(masterId);
        }
    }
    public Optional<Master> getMaster(Long userId){
        try (MasterDao dao = daoFactory.createMasterDao()) {
            return dao.getMaster(userId);
        }
    }
    public List<Master> getMastersByCategory(Long categoryId){
        try (MasterDao dao = daoFactory.createMasterDao()) {
            return dao.getMastersByCategory(categoryId);
        }
    }


    public void isProcedureAccordToMaster(Long masterId, Long procedureId) throws DiscrepancyException {
        try(MasterDao dao = daoFactory.createMasterDao()){
            dao.isProcedureAccordToMaster(masterId, procedureId);
        }
    }
    public void checkTimeForMaster(Long masterId, LocalTime time) throws DiscrepancyException {
        try(MasterDao dao = daoFactory.createMasterDao()){
            dao.checkTimeForMaster(masterId, time);
        }
    }
}
