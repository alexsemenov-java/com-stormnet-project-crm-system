package com.stormnet.crm.system.web.impl;

import com.stormnet.crm.system.obj.Act;
import com.stormnet.crm.system.serverservice.ActService;
import com.stormnet.crm.system.serverservice.factory.ServiceFactory;
import com.stormnet.crm.system.web.common.Command;
import com.stormnet.crm.system.web.common.Request;
import com.stormnet.crm.system.web.common.Response;
import com.stormnet.crm.system.web.socket.ResponseCode;

public class GetActCommand implements Command {

    @Override
    public void execute(Request request, Response response) {
        String idStr = (String) request.getParameter("id");
        Integer id = Integer.valueOf(idStr);

        if (idStr.equals("")) {
            System.out.println("Null id received");
            response.setResponseCode(ResponseCode.NotFoundCode);
            return;
        }

        ActService actService = ServiceFactory.getServiceFactory().getActService();
        Act act = actService.loadById(id);

        if (act == null) {
            System.out.println("Object not found");
            response.setResponseCode(ResponseCode.NotFoundCode);
            return;
        }

        response.getJsonWriter().key("response-bo");
        response.getJsonWriter().array();
        response.getJsonWriter().object();
        response.getJsonWriter().key("id").value(act.getId().toString());
        response.getJsonWriter().key("clientId").value(act.getClientId().toString());
        response.getJsonWriter().key("isFinished").value(act.getFinished().toString());
        response.getJsonWriter().key("managerLastName").value(act.getManagerLastName());
        response.getJsonWriter().key("managerFirstName").value(act.getManagerFirstName());
        response.getJsonWriter().key("clientLastName").value(act.getClientLastName());
        response.getJsonWriter().key("clientFirstName").value(act.getClientFirstName());
        response.getJsonWriter().key("managerId").value(act.getManagerId().toString());
        response.getJsonWriter().key("date").value(act.getDate().toString());
        response.getJsonWriter().key("time").value(act.getTime());
        response.getJsonWriter().key("office").value(act.getOffice());
        response.getJsonWriter().key("rating").value(act.getRating());
        response.getJsonWriter().key("clientComment").value(act.getClientComment());
        response.getJsonWriter().key("managerComment").value(act.getManagerComment());
        response.getJsonWriter().key("phoneNumber").value(act.getPhoneNumber());
        response.getJsonWriter().endObject();
        response.getJsonWriter().endArray();

        response.setResponseCode(ResponseCode.OkCode);
    }
}
