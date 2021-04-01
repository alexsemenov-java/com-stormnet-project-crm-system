package com.stormnet.crm.system.web.impl;

import com.stormnet.crm.system.obj.Act;
import com.stormnet.crm.system.serverservice.ActService;
import com.stormnet.crm.system.serverservice.factory.ServiceFactory;
import com.stormnet.crm.system.web.common.Command;
import com.stormnet.crm.system.web.common.Request;
import com.stormnet.crm.system.web.common.Response;
import com.stormnet.crm.system.web.socket.ResponseCode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UpdateActCommand implements Command {

    @Override
    public void execute(Request request, Response response) {

        String idStr = (String) request.getParameter("id");
        Integer id = Integer.valueOf(idStr);

        if (idStr.equals("")) {
            System.out.println("Null id received");
            response.setResponseCode(ResponseCode.NotFoundCode);
            return;
        }

        String clientIdStr = (String) request.getParameter("clientId");
        Integer clientId = Integer.valueOf(clientIdStr);

        String isFinishedStr = (String) request.getParameter("isFinished");
        Boolean isFinished = Boolean.valueOf(isFinishedStr);

        String managerLastNameStr = (String) request.getParameter("managerLastName");

        String managerFirstNameStr = (String) request.getParameter("managerFirstName");

        String clientLastNameStr = (String) request.getParameter("clientLastName");

        String clientFirstNameStr = (String) request.getParameter("clientFirstName");

        String dateStr = (String) request.getParameter("date");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        LocalDate date = LocalDate.parse(dateStr, formatter);

        String timeStr = (String) request.getParameter("time");

        String managerIdStr = (String) request.getParameter("managerId");
        Integer managerId = Integer.valueOf(managerIdStr);

        String officeStr = (String) request.getParameter("office");

        String ratingStr = (String) request.getParameter("rating");

        String clientCommentStr = (String) request.getParameter("clientComment");

        String managerCommentStr = (String) request.getParameter("managerComment");

        String phoneNumberStr = (String) request.getParameter("phoneNumber");

        Act act = new Act();
        act.setId(id);
        act.setFinished(isFinished);
        act.setClientId(clientId);
        act.setManagerLastName(managerLastNameStr);
        act.setManagerFirstName(managerFirstNameStr);
        act.setClientLastName(clientLastNameStr);
        act.setClientFirstName(clientFirstNameStr);
        act.setDate(date);
        act.setTime(timeStr);
        act.setManagerId(managerId);
        act.setOffice(officeStr);
        act.setRating(ratingStr);
        act.setClientComment(clientCommentStr);
        act.setManagerComment(managerCommentStr);
        act.setPhoneNumber(phoneNumberStr);

        ActService actService = ServiceFactory.getServiceFactory().getActService();
        actService.update(act);

        response.setResponseCode(ResponseCode.OkCode);
    }
}
