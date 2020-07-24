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

public class SaveStaffCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {

        Integer ID;
        if (request.getParameter("ID").equals(null)){
            ID = null;
        } else {
            ID = (int) request.getParameter("ID");
        }

        String name = request.getParameter("name").toString();
        String surname = request.getParameter("surname").toString();
        String position = request.getParameter("position").toString();
        double salary = ((Number)request.getParameter("salary")).doubleValue();

        Staff staff = new Staff();
        staff.setID(ID);
        staff.setName(name);
        staff.setSurname(surname);
        staff.setPosition(position);
        staff.setSalary(salary);

        ServiceFactory sf = ServiceFactory.getServiceFactory();
        StaffService ss = sf.getStaffService();
        ss.saveStaff(staff);
    }
}
