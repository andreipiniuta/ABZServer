package com.stormnet.andrewPiniuta.abz;

import com.stormnet.andrewPiniuta.abz.web.Command;
import com.stormnet.andrewPiniuta.abz.web.impl.CommandFactory;
import com.stormnet.andrewPiniuta.abz.web.requestResponse.Request;
import com.stormnet.andrewPiniuta.abz.web.requestResponse.Response;
import com.stormnet.andrewPiniuta.abz.web.requestResponse.ResponseCode;
import org.json.JSONTokener;
import org.json.JSONWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ServerAbz {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8848);//открыли соединение

        while (true) {//в бесконечн цикле
            Socket clientSocket = serverSocket.accept();//ждем запрос от Client

            InputStream clientIs = clientSocket.getInputStream();//открыли поток ввода у socket в Server
            JSONTokener tokener = new JSONTokener(clientIs);//создали tokener для чтения json


            OutputStream clientOs = clientSocket.getOutputStream();//открыли поток вывода у socket из Server-а
            OutputStreamWriter clientWriter = new OutputStreamWriter(clientOs);//перевод из текста в байты
            JSONWriter jsonWriter = new JSONWriter(clientWriter); // открыли JSONWriter


            Request request = new Request(tokener);//создали объект request(в нем имя команды и если есть бизнес объект в Map)
            Response response = new Response();//создали объект response(в нем имя кода ошибки и если есть бизнесдля отправк)
//получаем фабрику команд, создаем объект каманда
            CommandFactory commandFactory = CommandFactory.get();
            Command command = commandFactory.getCommand(request.getCommandName());//создаем команду с установкой какая команда пришла
//если нет команды отрпавляем json с кодом ошибки из Enum-a ошибок
            if (command == null) {
                response.setResponseCode(ResponseCode.NotFoundCode);
                jsonWriter.object();
                buildHeadersSection(jsonWriter, response);
                jsonWriter.endObject();

                clientWriter.close();
                clientSocket.close();
                continue;
            }

            try {  //если есть команда
                command.execute(request, response);//выполняем команду с запросом и ответом
            } catch (Exception e) {
                // Send 500 response
                e.printStackTrace();
                continue;
            }
//установка кода ОК из Enum-a
            response.setResponseCode(ResponseCode.OkCode);
//отправляем назад json
            jsonWriter.object();//создал json объект тут
            buildHeadersSection(jsonWriter, response);//метод по создание json объект-код ошибки
            buildResponseDataSection(jsonWriter, response);//метод по создание json объект-список бизнес объектов
            jsonWriter.endObject();//закрыл json объект

            clientWriter.close();//закрыли OutputStreamWriter
            clientSocket.close();//закрыли clientSocket из начала цикла while
        }
    }
//запись в json первого json-объекта где ключ "headers"  значение --- 2 пары "status-code" со знач  и "status-message" со знач
    private static void buildHeadersSection(JSONWriter jsonWriter, Response response) {
        jsonWriter.key("headers");

        jsonWriter.object();
        jsonWriter.key("status-code").value(response.getStatusCode());
        jsonWriter.key("status-message").value(response.getStatusMessage());
        jsonWriter.endObject();
    }
    //запись в json второго json-объекта где ключ "response-data", а значение массив наших бизнес объектов
    private static void buildResponseDataSection(JSONWriter jsonWriter, Response response) {
        jsonWriter.key("response-data");
        jsonWriter.array();//создали массив json объектов

        //в список карт ложим список карт из response
        List<Map<String, Object>> responseDataValue = response.getResponseDataValue();

        for (Map<String, Object> dataValue : responseDataValue) {//в цикле из списка карт достаем по одной карте

            jsonWriter.object();// создаем json объект

            Set<String> keySet = dataValue.keySet();//у карты достали ключи
            for (String key: keySet) {
                jsonWriter.key(key).value(dataValue.get(key));// по ключам достали их значения и положили в Json
            }

            jsonWriter.endObject();//закрыли json объект
        }

        jsonWriter.endArray(); //закрыли массив json объектов
    }
}
