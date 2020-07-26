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
import java.util.Map;

public class GetTruckByIDCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {
        int ID = (int) request.getParameter("ID");
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        TruckService ts = sf.getTruckService();
        Truck truck = ts.getTruckByID(ID);

        Map<String, Object> truckMap = new HashMap<>();
        truckMap.put("ID", truck.getID());
        truckMap.put("truckType", truck.getTruckType());
        truckMap.put("truckNumber", truck.getTruckNumber());
        truckMap.put("payload", truck.getPayload());
        response.addResponseData(truckMap);
    }
}
