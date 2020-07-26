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
import java.util.List;
import java.util.Map;

//в классах с реализацией метода Interface Command используем объекты Service
public class GetAllMaterialCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, SAXException, ParserConfigurationException {
//создали Service объект и вызвали метод getAllMaterial
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        MaterialService ms = sf.getMaterialService();

        List<Material> allMaterial = ms.getAllMaterial();
        //запись объекта через карту
        for (Material material : allMaterial) {
            Map<String, Object> materialMap = new HashMap<>();
            materialMap.put("ID", material.getID());
            materialMap.put("materialName", material.getMaterialName());
            materialMap.put("providerName", material.getProviderName());
            materialMap.put("amount", material.getAmount());
            materialMap.put("costPerOne", material.getCostPerOne());
            materialMap.put("totalCost", material.getTotalCost());
            //каждую карту кладём в response
            response.addResponseData(materialMap);
        }

    }
}
