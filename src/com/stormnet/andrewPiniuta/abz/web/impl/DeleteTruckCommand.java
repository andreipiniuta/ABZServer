package com.stormnet.andrewPiniuta.abz.web.impl;

import com.stormnet.andrewPiniuta.abz.service.ServiceFactory;
import com.stormnet.andrewPiniuta.abz.service.TruckService;
import com.stormnet.andrewPiniuta.abz.web.Command;
import com.stormnet.andrewPiniuta.abz.web.Request;
import com.stormnet.andrewPiniuta.abz.web.Response;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class DeleteTruckCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {
        Integer ID = (int)request.getParameter("ID");

        ServiceFactory sf = ServiceFactory.getServiceFactory();
        TruckService ts = sf.getTruckService();
        ts.deleteTruck(ID);
    }
}
