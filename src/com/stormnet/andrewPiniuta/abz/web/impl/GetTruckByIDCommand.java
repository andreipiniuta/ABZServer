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

public class GetTruckByIDCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {
        int ID = (int) request.getParameter("ID");
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        TruckService ts = sf.getTruckService();
        Truck truck = ts.getTruckByID(ID);

        JSONWriter jsonWriter = response.getJsonWriter();
        jsonWriter.object();
        jsonWriter.key("ID").value(truck.getID());
        jsonWriter.key("truckType").value(truck.getTruckType());
        jsonWriter.key("truckNumber").value(truck.getTruckNumber());
        jsonWriter.key("payload").value(truck.getPayload());
        jsonWriter.endObject();
    }
}
