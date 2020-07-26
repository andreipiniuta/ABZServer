package com.stormnet.andrewPiniuta.abz.web.impl;

import com.stormnet.andrewPiniuta.abz.data.Truck;
import com.stormnet.andrewPiniuta.abz.service.ServiceFactory;
import com.stormnet.andrewPiniuta.abz.service.TruckService;
import com.stormnet.andrewPiniuta.abz.web.Command;
import com.stormnet.andrewPiniuta.abz.web.Request;
import com.stormnet.andrewPiniuta.abz.web.Response;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllTruckCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        TruckService ts = sf.getTruckService();

        List<Truck> allTruck = ts.getAllTruck();

        //запись объекта через карту
        for (Truck truck : allTruck) {
            Map<String, Object> truckMap = new HashMap<>();
            truckMap.put("ID", truck.getID());
            truckMap.put("truckType", truck.getTruckType());
            truckMap.put("truckNumber", truck.getTruckNumber());
            truckMap.put("payload", truck.getPayload());
            //каждую карту кладём в response
            response.addResponseData(truckMap);
        }
    }
}
