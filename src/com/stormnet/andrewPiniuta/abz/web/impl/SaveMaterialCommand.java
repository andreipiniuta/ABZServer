package com.stormnet.andrewPiniuta.abz.web.impl;

import com.stormnet.andrewPiniuta.abz.data.Material;
import com.stormnet.andrewPiniuta.abz.data.MaterialName;
import com.stormnet.andrewPiniuta.abz.data.ProviderName;
import com.stormnet.andrewPiniuta.abz.service.MaterialService;
import com.stormnet.andrewPiniuta.abz.service.ServiceFactory;
import com.stormnet.andrewPiniuta.abz.web.Command;
import com.stormnet.andrewPiniuta.abz.web.Request;
import com.stormnet.andrewPiniuta.abz.web.Response;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
//в классах с реализацией метода Interface Command используем объекты Service
public class SaveMaterialCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {

//достаем из объекта request данные бизнесс объекта
         Integer ID;
        if (request.getParameter("ID") == null){
            ID = null;
        } else {
            ID = (int) request.getParameter("ID");
        }

        MaterialName materialName = MaterialName.valueOf(request.getParameter("materialName").toString());
        ProviderName providerName = ProviderName.valueOf(request.getParameter("providerName").toString());
        double amount = ((Number) request.getParameter("amount")).doubleValue();
        double costPerOne = ((Number) request.getParameter("costPerOne")).doubleValue();

//создание бизнес объекта
        Material material = new Material();
        material.setID(ID);
        material.setMaterialName(materialName);
        material.setProviderName(providerName);
        material.setAmount(amount);
        material.setCostPerOne(costPerOne);
        material.getTotalCost();
//создание сервис объектов и вызов их методов
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        MaterialService ms = sf.getMaterialService();
        ms.saveMaterial(material);
    }
}
