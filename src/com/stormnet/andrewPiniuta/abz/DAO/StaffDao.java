package com.stormnet.andrewPiniuta.abz.DAO;


import com.stormnet.andrewPiniuta.abz.data.Staff;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public interface StaffDao {
    public  void saveStaff(Staff staff) throws TransformerException, ParserConfigurationException, IOException, SAXException;

    List<Staff> loadAllStaff() throws ParserConfigurationException, IOException, SAXException;

    Staff loadStaffByID(Integer ID) throws IOException, SAXException, ParserConfigurationException;

    public  void updateStaff(Staff staff) throws ParserConfigurationException, IOException, SAXException, TransformerException;

    public void deleteStaff(Integer ID) throws IOException, SAXException, ParserConfigurationException, TransformerException;
}
