package com.stormnet.crm.system.dao.exception;

import com.stormnet.crm.system.dao.factory.DaoTypes;

public class UnknownDaoTypeException extends RuntimeException {

    public UnknownDaoTypeException(DaoTypes type) {
        super("Can not find DAO Factory for  " + type + "!");
    }
}
