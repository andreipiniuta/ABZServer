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
import java.util.List;

public class GetAllStaffCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        StaffService ss = sf.getStaffService();

        List<Staff> allStaff = ss.getAllStaff();

        JSONWriter jsonWriter = response.getJsonWriter();

        jsonWriter.array();
//из списка с объектами берем по одному и пихаем в json
        for (Staff staff : allStaff) {

            jsonWriter.object();
            jsonWriter.key("ID").value(staff.getID());
            jsonWriter.key("name").value(staff.getName());
            jsonWriter.key("surname").value(staff.getSurname());
            jsonWriter.key("position").value(staff.getPosition());
            jsonWriter.key("salary").value(staff.getSalary());
            jsonWriter.endObject();
        }

        jsonWriter.endArray();
    }
}
