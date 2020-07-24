package com.stormnet.andrewPiniuta.abz.web.impl;

import com.stormnet.andrewPiniuta.abz.data.Truck;
import com.stormnet.andrewPiniuta.abz.service.ServiceFactory;
import com.stormnet.andrewPiniuta.abz.service.TruckService;
import com.stormnet.andrewPiniuta.abz.web.Command;
import com.stormnet.andrewPiniuta.abz.web.Request;
import com.stormnet.andrewPiniuta.abz.web.Response;
import org.json.JSONWriter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class GetAllTruckCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        TruckService ts = sf.getTruckService();

        List<Truck> allTruck = ts.getAllTruck();

        JSONWriter jsonWriter = response.getJsonWriter();

        jsonWriter.array();
//из списка с объектами берем по одному и пихаем в json
        for (Truck truck : allTruck) {

            jsonWriter.object();
            jsonWriter.key("ID").value(truck.getID());
            jsonWriter.key("truckType").value(truck.getTruckType());
            jsonWriter.key("truckNumber").value(truck.getTruckNumber());
            jsonWriter.key("payload").value(truck.getPayload());
            jsonWriter.endObject();
        }

        jsonWriter.endArray();
    }
}
