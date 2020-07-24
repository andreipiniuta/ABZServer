package com.stormnet.andrewPiniuta.abz.DAO;


import com.stormnet.andrewPiniuta.abz.data.Truck;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public interface TruckDao {

    public  void saveTruck(Truck truck) throws ParserConfigurationException, IOException, SAXException, TransformerException;

    List<Truck> loadAllTruck() throws ParserConfigurationException, IOException, SAXException;

    Truck loadTruckByID(Integer ID) throws IOException, SAXException, ParserConfigurationException;

    public  void updateTruck(Truck truck) throws ParserConfigurationException, SAXException, IOException, TransformerException;

    public void deleteTruck(Integer ID) throws IOException, SAXException, ParserConfigurationException, TransformerException;
}
