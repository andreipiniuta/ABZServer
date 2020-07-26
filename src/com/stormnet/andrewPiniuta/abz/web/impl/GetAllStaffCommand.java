package com.stormnet.andrewPiniuta.abz.web.impl;

import com.stormnet.andrewPiniuta.abz.data.Staff;
import com.stormnet.andrewPiniuta.abz.service.ServiceFactory;
import com.stormnet.andrewPiniuta.abz.service.StaffService;
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

public class GetAllStaffCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        StaffService ss = sf.getStaffService();

        List<Staff> allStaff = ss.getAllStaff();

        //запись объекта через карту
        for (Staff staff : allStaff) {
            Map<String, Object> staffMap = new HashMap<>();
            staffMap.put("ID", staff.getID());
            staffMap.put("name", staff.getName());
            staffMap.put("surname", staff.getSurname());
            staffMap.put("position", staff.getPosition());
            staffMap.put("salary", staff.getSalary());

            //каждую карту кладём в response
            response.addResponseData(staffMap);
        }
    }
}
