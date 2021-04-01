package com.stormnet.crm.system.web.common;

public interface Request {

    String getCommandName();

    Object getParameter(String paramName);
}
