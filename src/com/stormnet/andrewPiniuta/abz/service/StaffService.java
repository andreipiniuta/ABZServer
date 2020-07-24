package com.stormnet.andrewPiniuta.abz.service;


import com.stormnet.andrewPiniuta.abz.data.Staff;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public interface StaffService {

    public  void saveStaff(Staff staff) throws ParserConfigurationException, IOException, SAXException, TransformerException;

    List<Staff> getAllStaff() throws IOException, SAXException, ParserConfigurationException;

    Staff getStaffByID(Integer ID) throws ParserConfigurationException, SAXException, IOException;

    public void deleteStaff(Integer ID) throws SAXException, TransformerException, ParserConfigurationException, IOException;
}
