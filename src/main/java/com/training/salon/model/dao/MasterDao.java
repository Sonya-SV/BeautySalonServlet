package com.training.salon.model.dao;

import com.training.salon.controller.exception.DiscrepancyException;
import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.Procedure;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface MasterDao extends GenericDao<Master>{

    Optional<Master> getMaster(Long userId);

    List<Master> getMastersByCategory(Long categoryId);

    void isProcedureAccordToMaster(Long masterId, Long procedureId) throws DiscrepancyException;

    void checkTimeForMaster(Long masterId, LocalTime time) throws DiscrepancyException;

    List<Master> getAllByPage(int start, int end);

    int getCountMaster();
}
