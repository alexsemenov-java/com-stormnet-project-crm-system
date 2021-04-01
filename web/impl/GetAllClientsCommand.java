package com.stormnet.crm.system.web.impl;

import com.stormnet.crm.system.obj.Client;
import com.stormnet.crm.system.serverservice.PersonService;
import com.stormnet.crm.system.serverservice.factory.ServiceFactory;
import com.stormnet.crm.system.web.common.Command;
import com.stormnet.crm.system.web.common.Request;
import com.stormnet.crm.system.web.common.Response;
import com.stormnet.crm.system.web.socket.ResponseCode;

import java.util.List;

public class GetAllClientsCommand implements Command {

    @Override
    public void execute(Request request, Response response) {

        PersonService<Client> personService = ServiceFactory.getServiceFactory().getClientService();
        List<Client> persons = personService.loadAll();

        response.getJsonWriter().key("response-bo");
        response.getJsonWriter().array();

        for (Client person : persons) {

            response.getJsonWriter().object();
            response.getJsonWriter().key("id").value(person.getId().toString());
            response.getJsonWriter().key("lastName").value(person.getLastName());
            response.getJsonWriter().key("firstName").value(person.getFirstName());
            response.getJsonWriter().key("login").value(person.getLogin());
            response.getJsonWriter().key("password").value(person.getPassword());
            response.getJsonWriter().key("phoneNumber").value(person.getPhoneNumber());
            response.getJsonWriter().endObject();
        }

        response.getJsonWriter().endArray();
        response.setResponseCode(ResponseCode.OkCode);
    }
}
