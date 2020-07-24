package com.stormnet.andrewPiniuta.abz.web.impl;

import com.stormnet.andrewPiniuta.abz.data.Staff;
import com.stormnet.andrewPiniuta.abz.service.ServiceFactory;
import com.stormnet.andrewPiniuta.abz.service.StaffService;
import com.stormnet.andrewPiniuta.abz.web.Command;
import com.stormnet.andrewPiniuta.abz.web.Request;
import com.stormnet.andrewPiniuta.abz.web.Response;
import org.json.JSONWriter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class GetStaffByIDCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {
        int ID = (int) request.getParameter("ID");
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        StaffService ss = sf.getStaffService();
        Staff staff = ss.getStaffByID(ID);

        JSONWriter jsonWriter = response.getJsonWriter();
        jsonWriter.object();
        jsonWriter.key("ID").value(staff.getID());
        jsonWriter.key("name").value(staff.getName());
        jsonWriter.key("surname").value(staff.getSurname());
        jsonWriter.key("position").value(staff.getPosition());
        jsonWriter.key("salary").value(staff.getSalary());
        jsonWriter.endObject();
    }
}
