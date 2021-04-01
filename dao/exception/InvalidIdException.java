package com.stormnet.crm.system.dao.exception;

public class InvalidIdException extends RuntimeException{

    public InvalidIdException() {
        super("This ID does not exist!");
    }
}
