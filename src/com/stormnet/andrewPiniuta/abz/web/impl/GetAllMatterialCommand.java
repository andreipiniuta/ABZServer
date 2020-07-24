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
import java.util.List;
//в классах с реализацией метода Interface Command используем объекты Service
public class GetAllMatterialCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, SAXException, ParserConfigurationException {
//создали Service объект и вызвали метод getAllMaterial
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        MaterialService ms = sf.getMaterialService();

        List<Material> allMaterial = ms.getAllMaterial();

        JSONWriter jsonWriter = response.getJsonWriter();

        jsonWriter.array();
//из списка с объектами берем по одному и пихаем в json
        for (Material material : allMaterial) {

            jsonWriter.object();
            jsonWriter.key("ID").value(material.getID());
            jsonWriter.key("materialName").value(material.getMaterialName());
            jsonWriter.key("providerName").value(material.getProviderName());
            jsonWriter.key("amount").value(material.getAmount());
            jsonWriter.key("costPerOne").value(material.getCostPerOne());
            jsonWriter.key("totalCost").value(material.getTotalCost());
            jsonWriter.endObject();
        }

        jsonWriter.endArray();

    }
}
