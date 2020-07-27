package com.stormnet.andrewPiniuta.abz.web.requestResponse;

import org.json.JSONWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//реализация методов Interface
public class Response implements com.stormnet.andrewPiniuta.abz.web.Response {
    private int statusCode;// код ошибки

    private String statusMessage;// сообщение об ошибке

    private List<Map<String, Object>> responseData = new ArrayList<>();//список карт бизнес объектов

//    private JSONWriter writer;

//    public Response(JSONWriter writer) {//конструктор
//        this.writer = writer;
//    }

    @Override
    public int getStatusCode() {//возвр кодп
        return statusCode;
    }

    @Override
    public String getStatusMessage() {//возвр сообщения
        return statusMessage;
    }

    public void setResponseCode(ResponseCode code) {//установка кода и сообщения
        this.statusCode = code.getCode();
        this.statusMessage = code.getMessage();
    }

    @Override
    public void addResponseData(Map<String, Object> data) {//установка карты с бизнес объектом
        this.responseData.add(data);
    }

    @Override
    public List<Map<String, Object>> getResponseDataValue() {//геттер по получению списка карта с бизнесс объектом
        return responseData;
    }

//    @Override
//    public JSONWriter getJsonWriter() {//геттер writer
//        return writer;
//    }
}
