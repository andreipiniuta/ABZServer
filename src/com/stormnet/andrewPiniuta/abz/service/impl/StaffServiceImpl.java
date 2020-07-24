package com.stormnet.andrewPiniuta.abz.service.impl;

import com.stormnet.andrewPiniuta.abz.DAO.DaoFactory;
import com.stormnet.andrewPiniuta.abz.DAO.EnumFactory;
import com.stormnet.andrewPiniuta.abz.DAO.StaffDao;
import com.stormnet.andrewPiniuta.abz.data.Staff;
import com.stormnet.andrewPiniuta.abz.service.StaffService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class StaffServiceImpl implements StaffService {

    @Override
    public void saveStaff(Staff staff) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
        StaffDao dao = df.getStaffDao();
        Integer id = staff.getID();
        if(id == null){
            dao.saveStaff(staff);
        } else{
            dao.updateStaff(staff);
        }

    }

    @Override
    public List<Staff> getAllStaff() throws IOException, SAXException, ParserConfigurationException {
        DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
        StaffDao dao = df.getStaffDao();
        return dao.loadAllStaff();
    }

    @Override
    public Staff getStaffByID(Integer ID) throws ParserConfigurationException, SAXException, IOException {
    DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
    StaffDao dao = df.getStaffDao();
        return dao.loadStaffByID(ID);
    }

    @Override
    public void deleteStaff(Integer ID) throws SAXException, TransformerException, ParserConfigurationException, IOException {
        DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
        StaffDao dao = df.getStaffDao();
        dao.deleteStaff(ID);

    }
}
