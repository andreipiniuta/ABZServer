package com.stormnet.andrewPiniuta.abz.service.impl;

import com.stormnet.andrewPiniuta.abz.DAO.DaoFactory;
import com.stormnet.andrewPiniuta.abz.DAO.EnumFactory;
import com.stormnet.andrewPiniuta.abz.DAO.TruckDao;

import com.stormnet.andrewPiniuta.abz.data.Truck;
import com.stormnet.andrewPiniuta.abz.service.TruckService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class TruckServiceImpl implements TruckService {
    @Override
    public void saveTruck(Truck truck) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
        TruckDao dao = df.getTruckDao();
        Integer id = truck.getID();
        if (id == null) {
            dao.saveTruck(truck);
        } else {
            dao.updateTruck(truck);
        }
    }

    @Override
    public List<Truck> getAllTruck() throws IOException, SAXException, ParserConfigurationException {
        DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
        TruckDao dao = df.getTruckDao();
        return dao.loadAllTruck();
    }

    @Override
    public Truck getTruckByID(Integer ID) throws ParserConfigurationException, SAXException, IOException {
        DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
        TruckDao dao = df.getTruckDao();
        return dao.loadTruckByID(ID);
    }

    @Override
    public void deleteTruck(Integer ID) throws SAXException, TransformerException, ParserConfigurationException, IOException {
        DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
        TruckDao dao = df.getTruckDao();
        dao.deleteTruck(ID);

    }
}
