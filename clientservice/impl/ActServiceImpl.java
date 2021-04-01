package com.stormnet.crm.system.clientservice.impl;

import com.stormnet.crm.system.clientservice.ActService;
import com.stormnet.crm.system.obj.Act;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ActServiceImpl implements ActService {
    public ActServiceImpl() {

    }

    @Override
    public void save(Act act) throws IOException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        Socket socket = new Socket(address, 1111);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        JSONWriter jsonWriter = new JSONWriter(writer);

        jsonWriter.object();
        jsonWriter.key("headers");

        jsonWriter.object();
        jsonWriter.key("command-name").value("save-act");
        jsonWriter.endObject();

        jsonWriter.key("parameters");
        jsonWriter.object();

        jsonWriter.key("isFinished").value(String.valueOf(act.getFinished()));
        jsonWriter.key("clientId").value(String.valueOf(act.getClientId()));
        jsonWriter.key("managerLastName").value(act.getManagerLastName());
        jsonWriter.key("managerFirstName").value(act.getManagerFirstName());
        jsonWriter.key("clientLastName").value(act.getClientLastName());
        jsonWriter.key("clientFirstName").value(act.getClientFirstName());
        jsonWriter.key("date").value((act.getDate().toString()));
        jsonWriter.key("time").value(act.getTime());
        jsonWriter.key("managerId").value(String.valueOf(act.getManagerId()));
        jsonWriter.key("office").value(act.getOffice());
        jsonWriter.key("rating").value(act.getRating());
        jsonWriter.key("clientComment").value(act.getClientComment());
        jsonWriter.key("managerComment").value(act.getManagerComment());
        jsonWriter.key("phoneNumber").value(act.getPhoneNumber());

        jsonWriter.endObject();
        jsonWriter.endObject();

        writer.flush();

        InputStream is = socket.getInputStream();
        JSONTokener tokener = new JSONTokener(is);

        JSONObject response = (JSONObject) tokener.nextValue();
        JSONObject headers = response.getJSONObject("headers");

        int code = headers.getInt("status-code");
        String message = headers.getString("status-message");

        System.out.println(code + " - " + message);

        writer.close();
        is.close();
        socket.close();
    }


    @Override
    public List<Act> loadAll() throws IOException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        Socket socket = new Socket(address, 1111);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        JSONWriter jsonWriter = new JSONWriter(writer);

        jsonWriter.object();
        jsonWriter.key("headers");

        jsonWriter.object();
        jsonWriter.key("command-name").value("get-all-acts");
        jsonWriter.endObject();

        jsonWriter.key("parameters");
        jsonWriter.object();

        jsonWriter.endObject();
        jsonWriter.endObject();

        writer.flush();

        InputStream is = socket.getInputStream();
        JSONTokener tokener = new JSONTokener(is);

        JSONObject response = (JSONObject) tokener.nextValue();
        JSONObject headers = response.getJSONObject("headers");

        int code = headers.getInt("status-code");
        String message = headers.getString("status-message");

        System.out.println(code + " - " + message);

        List<Act> allActs = new ArrayList<>();
        if (code == 200) {

            JSONArray responseData = response.getJSONArray("response-bo");
            int length = responseData.length();

            for (int i = 0; i < length; i++) {
                JSONObject object = (JSONObject) responseData.get(i);

                String IdStr = (String) object.get("id");
                Integer id = Integer.valueOf(IdStr);

                String clientIdStr = (String) object.get("clientId");
                Integer clientId = Integer.valueOf(clientIdStr);

                String isFinishedStr = (String) object.get("isFinished");
                Boolean isFinished = Boolean.valueOf(isFinishedStr);

                String managerLastNameStr = (String) object.get("managerLastName");

                String managerFirstNameStr = (String) object.get("managerFirstName");

                String clientLastNameStr = (String) object.get("clientLastName");

                String clientFirstNameStr = (String) object.get("clientFirstName");

                String dateStr = (String) object.get("date");
                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
                LocalDate date = LocalDate.parse(dateStr, formatter);

                String timeStr = (String) object.get("time");

                String managerIdStr = (String) object.get("managerId");
                Integer managerId = Integer.valueOf(managerIdStr);

                String officeStr = (String) object.get("office");

                String ratingStr = (String) object.get("rating");

                String clientCommentStr = (String) object.get("clientComment");

                String managerCommentStr = (String) object.get("managerComment");

                String phoneNumberStr = (String) object.get("phoneNumber");

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

                allActs.add(act);
            }
        }
        writer.close();
        is.close();
        socket.close();
        return allActs;
    }

    @Override
    public Act loadById(Integer id) throws IOException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        Socket socket = new Socket(address, 1111);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        JSONWriter jsonWriter = new JSONWriter(writer);

        jsonWriter.object();
        jsonWriter.key("headers");

        jsonWriter.object();
        jsonWriter.key("command-name").value("get-act");
        jsonWriter.endObject();

        jsonWriter.key("parameters");
        jsonWriter.object();

        jsonWriter.key("id").value(String.valueOf(id));

        jsonWriter.endObject();

        jsonWriter.endObject();

        writer.flush();

        InputStream is = socket.getInputStream();
        JSONTokener tokener = new JSONTokener(is);

        JSONObject response = (JSONObject) tokener.nextValue();
        JSONObject headers = response.getJSONObject("headers");

        int code = headers.getInt("status-code");
        String message = headers.getString("status-message");

        System.out.println(code + " - " + message);

        Act act = new Act();

        if (code == 200) {

            JSONArray responseData = response.getJSONArray("response-bo");
            JSONObject object = (JSONObject) responseData.get(0);

            String clientIdStr = (String) object.get("clientId");
            Integer clientId = Integer.valueOf(clientIdStr);

            String isFinishedStr = (String) object.get("isFinished");
            Boolean isFinished = Boolean.valueOf(isFinishedStr);

            String managerLastNameStr = (String) object.get("managerLastName");

            String managerFirstNameStr = (String) object.get("managerFirstName");

            String clientLastNameStr = (String) object.get("clientLastName");

            String clientFirstNameStr = (String) object.get("clientFirstName");

            String dateStr = (String) object.get("date");
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            LocalDate date = LocalDate.parse(dateStr, formatter);

            String timeStr = (String) object.get("time");

            String managerIdStr = (String) object.get("managerId");
            Integer managerId = Integer.valueOf(managerIdStr);

            String officeStr = (String) object.get("office");

            String ratingStr = (String) object.get("rating");

            String clientCommentStr = (String) object.get("clientComment");

            String managerCommentStr = (String) object.get("managerComment");

            String phoneNumberStr = (String) object.get("phoneNumber");

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

        }
        writer.close();
        is.close();
        socket.close();
        return act;
    }

    @Override
    public void delete(Integer id) throws IOException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        Socket socket = new Socket(address, 1111);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        JSONWriter jsonWriter = new JSONWriter(writer);

        jsonWriter.object();
        jsonWriter.key("headers");

        jsonWriter.object();
        jsonWriter.key("command-name").value("delete-act");
        jsonWriter.endObject();

        jsonWriter.key("parameters");
        jsonWriter.object();

        jsonWriter.key("id").value(String.valueOf(id));

        jsonWriter.endObject();

        jsonWriter.endObject();

        writer.flush();

        InputStream is = socket.getInputStream();
        JSONTokener tokener = new JSONTokener(is);

        JSONObject response = (JSONObject) tokener.nextValue();
        JSONObject headers = response.getJSONObject("headers");

        int code = headers.getInt("status-code");
        String message = headers.getString("status-message");

        System.out.println(code + " - " + message);

        writer.close();
        is.close();
        socket.close();
    }

    @Override
    public void update(Act act) throws IOException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        Socket socket = new Socket(address, 1111);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        JSONWriter jsonWriter = new JSONWriter(writer);

        jsonWriter.object();
        jsonWriter.key("headers");

        jsonWriter.object();
        jsonWriter.key("command-name").value("update-act");
        jsonWriter.endObject();

        jsonWriter.key("parameters");
        jsonWriter.object();

        jsonWriter.key("id").value(String.valueOf(act.getId()));
        jsonWriter.key("isFinished").value(String.valueOf(act.getFinished()));
        jsonWriter.key("clientId").value(String.valueOf(act.getClientId()));
        jsonWriter.key("managerLastName").value(act.getManagerLastName());
        jsonWriter.key("managerFirstName").value(act.getManagerFirstName());
        jsonWriter.key("clientLastName").value(act.getClientLastName());
        jsonWriter.key("clientFirstName").value(act.getClientFirstName());
        jsonWriter.key("date").value((act.getDate().toString()));
        jsonWriter.key("time").value(act.getTime());
        jsonWriter.key("managerId").value(String.valueOf(act.getManagerId()));
        jsonWriter.key("office").value(act.getOffice());
        jsonWriter.key("rating").value(act.getRating());
        jsonWriter.key("clientComment").value(act.getClientComment());
        jsonWriter.key("managerComment").value(act.getManagerComment());
        jsonWriter.key("phoneNumber").value(act.getPhoneNumber());

        jsonWriter.endObject();
        jsonWriter.endObject();

        writer.flush();

        InputStream is = socket.getInputStream();
        JSONTokener tokener = new JSONTokener(is);

        JSONObject response = (JSONObject) tokener.nextValue();
        JSONObject headers = response.getJSONObject("headers");

        int code = headers.getInt("status-code");
        String message = headers.getString("status-message");

        System.out.println(code + " - " + message);

        writer.close();
        is.close();
        socket.close();
    }
}
