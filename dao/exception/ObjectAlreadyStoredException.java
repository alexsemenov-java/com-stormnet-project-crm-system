package com.stormnet.crm.system.dao.exception;

public class ObjectAlreadyStoredException extends RuntimeException{

    public ObjectAlreadyStoredException() {
        super("Object is Already Stored!");
    }
}
