package com.stormnet.crm.system.web.common;

public interface Command {

    void execute(Request request, Response response);
}
