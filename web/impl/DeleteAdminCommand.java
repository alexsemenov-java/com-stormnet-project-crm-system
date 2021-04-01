package com.stormnet.crm.system.web.impl;

import com.stormnet.crm.system.obj.Admin;
import com.stormnet.crm.system.serverservice.PersonService;
import com.stormnet.crm.system.serverservice.factory.ServiceFactory;
import com.stormnet.crm.system.web.common.Command;
import com.stormnet.crm.system.web.common.Request;
import com.stormnet.crm.system.web.common.Response;
import com.stormnet.crm.system.web.socket.ResponseCode;

public class DeleteAdminCommand implements Command {

    @Override
    public void execute(Request request, Response response) {

        String idStr = (String) request.getParameter("id");
        Integer id = Integer.valueOf(idStr);

        if (idStr.equals("")) {
            System.out.println("Null id received");
            response.setResponseCode(ResponseCode.NotFoundCode);
            return;
        }

        PersonService<Admin> personService = ServiceFactory.getServiceFactory().getAdminService();
        personService.delete(id);

        response.setResponseCode(ResponseCode.OkCode);
    }
}
