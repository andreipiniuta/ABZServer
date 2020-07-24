package com.stormnet.andrewPiniuta.abz.web.impl;

import com.stormnet.andrewPiniuta.abz.data.Material;
import com.stormnet.andrewPiniuta.abz.service.MaterialService;
import com.stormnet.andrewPiniuta.abz.service.ServiceFactory;
import com.stormnet.andrewPiniuta.abz.web.Command;
import com.stormnet.andrewPiniuta.abz.web.Request;
import com.stormnet.andrewPiniuta.abz.web.Response;
import org.json.JSONWriter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
//в классах с реализацией метода Interface Command используем объекты Service
public class GetMaterialByIDCommand implements Command {

    @Override
    public void execute(Request request, Response response) throws IOException, SAXException, ParserConfigurationException {

        int ID = (int) request.getParameter("ID");
//создали Service объект и вызвали метод getMaterialbyID

        ServiceFactory sf = ServiceFactory.getServiceFactory();
        MaterialService ms = sf.getMaterialService();
        Material material = ms.getMaterialbyID(ID);

        //запись объекта через карту

//        Map<String, Object> MaterialMap = new HashMap<>();
//в карту кладем ключи и значения бизнес объекта
//        MaterialMap.put("ID", m.getID());
//        MaterialMap.put("materialName", m.getMaterialName());
//        MaterialMap.put("providerName", m.getProviderName());
//        MaterialMap.put("amount", m.getAmount());
//        MaterialMap.put("costPerOne", m.getCostPerOne());
//        MaterialMap.put("totalCost", m.getTotalCost());
//        response.addResponseData(MaterialMap);

        //запись объекта через JSON

        JSONWriter jsonWriter = response.getJsonWriter();
        jsonWriter.object();
        jsonWriter.key("ID").value(material.getID());
        jsonWriter.key("materialName").value(material.getMaterialName());
        jsonWriter.key("providerName").value(material.getProviderName());
        jsonWriter.key("amount").value(material.getAmount());
        jsonWriter.key("costPerOne").value(material.getCostPerOne());
        jsonWriter.key("totalCost").value(material.getTotalCost());
        jsonWriter.endObject();

    }
}
