package com.stormnet.crm.system.web.common;

import com.stormnet.crm.system.web.socket.ResponseCode;
import org.json.JSONWriter;

import java.util.List;
import java.util.Map;

public interface Response {

    void setResponseCode(ResponseCode code);

    int getStatusCode();

    String getStatusMessage();

    void addResponseData(Map<String, Object> data);

    List<Map<String, Object>> getResponseDataValue();

    JSONWriter getJsonWriter();

    void setJsonWriter(JSONWriter writer);
}
