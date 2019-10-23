package com.training.salon.model.dao;

import com.training.salon.model.dao.impl.JDBCDaoFactory;
import com.training.salon.model.entity.Procedure;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract ProcedureDao createProcedureDao();
    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
