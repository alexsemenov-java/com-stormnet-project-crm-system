package com.stormnet.crm.system.web.impl;

import com.stormnet.crm.system.obj.Manager;
import com.stormnet.crm.system.serverservice.PersonService;
import com.stormnet.crm.system.serverservice.factory.ServiceFactory;
import com.stormnet.crm.system.web.common.Command;
import com.stormnet.crm.system.web.common.Request;
import com.stormnet.crm.system.web.common.Response;
import com.stormnet.crm.system.web.socket.ResponseCode;

public class SaveManagerCommand implements Command {

    @Override
    public void execute(Request request, Response response) {

        String lastNameStr = (String) request.getParameter("lastName");

        String firstNameStr = (String) request.getParameter("firstName");

        String loginStr = (String) request.getParameter("login");

        String passwordStr = (String) request.getParameter("password");

        String officeStr = (String) request.getParameter("office");

        Manager person = new Manager();
        person.setFirstName(firstNameStr);
        person.setLastName(lastNameStr);
        person.setLogin(loginStr);
        person.setPassword(passwordStr);
        person.setOffice(officeStr);

        PersonService<Manager> personService = ServiceFactory.getServiceFactory().getManagerService();
        personService.save(person);

        response.setResponseCode(ResponseCode.OkCode);
    }
}
