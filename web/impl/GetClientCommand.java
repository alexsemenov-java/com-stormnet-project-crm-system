package com.stormnet.crm.system.web.impl;

import com.stormnet.crm.system.obj.Client;
import com.stormnet.crm.system.serverservice.PersonService;
import com.stormnet.crm.system.serverservice.factory.ServiceFactory;
import com.stormnet.crm.system.web.common.Command;
import com.stormnet.crm.system.web.common.Request;
import com.stormnet.crm.system.web.common.Response;
import com.stormnet.crm.system.web.socket.ResponseCode;

public class GetClientCommand implements Command {

    @Override
    public void execute(Request request, Response response) {
        String idStr = (String) request.getParameter("id");
        Integer id = Integer.valueOf(idStr);

        if (idStr.equals("")) {
            System.out.println("Null id received");
            response.setResponseCode(ResponseCode.NotFoundCode);
            return;
        }

        PersonService<Client> personService = ServiceFactory.getServiceFactory().getClientService();
        Client person = personService.loadById(id);

        if (person == null) {
            System.out.println("Object not found");
            response.setResponseCode(ResponseCode.NotFoundCode);
            return;
        }

        response.getJsonWriter().key("response-bo");
        response.getJsonWriter().array();
        response.getJsonWriter().object();
        response.getJsonWriter().key("id").value(person.getId().toString());
        response.getJsonWriter().key("lastName").value(person.getLastName());
        response.getJsonWriter().key("firstName").value(person.getFirstName());
        response.getJsonWriter().key("login").value(person.getLogin());
        response.getJsonWriter().key("password").value(person.getPassword());
        response.getJsonWriter().key("phoneNumber").value(person.getPhoneNumber());
        response.getJsonWriter().endObject();
        response.getJsonWriter().endArray();

        response.setResponseCode(ResponseCode.OkCode);
    }
}
