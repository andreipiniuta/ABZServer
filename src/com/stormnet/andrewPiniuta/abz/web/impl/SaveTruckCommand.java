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

public class SaveTruckCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {

        Integer ID;
        if (request.getParameter("ID").equals(null)){
            ID = null;
        } else {
            ID = (int) request.getParameter("ID");
        }

        String truckType = request.getParameter("truckType").toString();
        String truckNumber = request.getParameter("truckNumber").toString();
        double payload = ((Number)request.getParameter("payload")).doubleValue();

        Truck truck = new Truck();
        truck.setID(ID);
        truck.setTruckType(truckType);
        truck.setTruckNumber(truckNumber);
        truck.setPayload(payload);

        ServiceFactory sf = ServiceFactory.getServiceFactory();
        TruckService ts = sf.getTruckService();
        ts.saveTruck(truck);
    }
}
