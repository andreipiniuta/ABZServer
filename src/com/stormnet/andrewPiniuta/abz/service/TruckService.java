package com.stormnet.andrewPiniuta.abz.service;


import com.stormnet.andrewPiniuta.abz.data.Truck;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public interface TruckService {

    public  void saveTruck(Truck truck) throws ParserConfigurationException, TransformerException, SAXException, IOException;

    List<Truck> getAllTruck() throws IOException, SAXException, ParserConfigurationException;

    Truck getTruckByID(Integer ID) throws ParserConfigurationException, SAXException, IOException;

    public void deleteTruck(Integer ID) throws SAXException, TransformerException, ParserConfigurationException, IOException;
}
