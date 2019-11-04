package com.training.salon.model.dao;

import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.Procedure;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MasterDao extends GenericDao<Master>{

    Optional<Master> getMaster(Long userId);
}
