package com.stormnet.andrewPiniuta.abz.web;

import com.stormnet.andrewPiniuta.abz.web.requestResponse.ResponseCode;
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
}
