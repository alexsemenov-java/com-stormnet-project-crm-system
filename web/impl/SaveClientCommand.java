package com.stormnet.crm.system.web.impl;

import com.stormnet.crm.system.obj.Client;
import com.stormnet.crm.system.serverservice.PersonService;
import com.stormnet.crm.system.serverservice.factory.ServiceFactory;
import com.stormnet.crm.system.web.common.Command;
import com.stormnet.crm.system.web.common.Request;
import com.stormnet.crm.system.web.common.Response;
import com.stormnet.crm.system.web.socket.ResponseCode;

public class SaveClientCommand implements Command {

    @Override
    public void execute(Request request, Response response) {

        String lastNameStr = (String) request.getParameter("lastName");

        String firstNameStr = (String) request.getParameter("firstName");

        String loginStr = (String) request.getParameter("login");

        String passwordStr = (String) request.getParameter("password");

        String phoneNumberStr = (String) request.getParameter("phoneNumber");

        Client person = new Client();
        person.setFirstName(firstNameStr);
        person.setLastName(lastNameStr);
        person.setLogin(loginStr);
        person.setPassword(passwordStr);
        person.setPhoneNumber(phoneNumberStr);

        PersonService<Client> personService = ServiceFactory.getServiceFactory().getClientService();
        personService.save(person);

        response.setResponseCode(ResponseCode.OkCode);
    }
}
