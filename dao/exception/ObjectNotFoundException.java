package com.stormnet.crm.system.dao.exception;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException() {
        super("Object not found!");
    }
}
