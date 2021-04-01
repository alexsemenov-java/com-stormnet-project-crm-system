package com.stormnet.crm.system.db.xml;

public class XmlDbException extends RuntimeException{

    public XmlDbException(Throwable cause) {
        super("Error occurred during access to XML DB", cause);

    }
}
