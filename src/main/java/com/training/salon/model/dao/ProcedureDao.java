package com.training.salon.model.dao;

import com.training.salon.model.entity.Category;
import com.training.salon.model.entity.Procedure;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProcedureDao extends GenericDao<Procedure> {

    List<Procedure> findAllByCategory(Category category);
}
