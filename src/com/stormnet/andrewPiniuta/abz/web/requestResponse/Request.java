package com.stormnet.andrewPiniuta.abz.web.requestResponse;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
//реализация методов Interface
public class Request implements com.stormnet.andrewPiniuta.abz.web.Request {

    private String commandName;// поле команда

    private Map<String, Object> parameters = new HashMap<>();//карта полученных бизнес объектов

    public Request(JSONTokener clientJson) {//чтение пришедшего Json
        JSONObject clientRequest = (JSONObject) clientJson.nextValue();
//читаем секцию с командой
        JSONObject clientHeaders = clientRequest.getJSONObject("headers");
        this.commandName = clientHeaders.getString("command-name");

        if (commandName.equals("get-all-material") || commandName.equals("get-material-by-id") ||
            commandName.equals("get-all-recipe") || commandName.equals("get-recipe-by-id") ||
            commandName.equals("get-all-product") || commandName.equals("get-product-by-id")||
            commandName.equals("get-all-staff") || commandName.equals("get-staff-by-id") ||
            commandName.equals("get-all-truck") || commandName.equals("get-truck-by-id")){
            return;
        }
        //читаем секцию с бизнес объектом
        JSONObject clientParameters = clientRequest.getJSONObject("parameters");

        Set<String> paramNames = clientParameters.keySet();//установка ключей карты в лист
        for (String paramName : paramNames) {
            Object paramValue = clientParameters.get(paramName);//установка значений ключей
            parameters.put(paramName, paramValue);
        }
    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public Object getParameter(String paramName) {
        return parameters.get(paramName);
    }
}
