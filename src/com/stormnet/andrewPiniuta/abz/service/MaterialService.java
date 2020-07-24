package com.stormnet.andrewPiniuta.abz.service;

import com.stormnet.andrewPiniuta.abz.data.Material;
import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

//методы теже что в DAO кроме update, его нет,
public interface MaterialService {

    public  void saveMaterial(Material material) throws IOException, ParserConfigurationException, SAXException, TransformerException;

    List<Material> getAllMaterial() throws ParserConfigurationException, SAXException, IOException;//вместо load-get

    Material getMaterialbyID(Integer ID) throws ParserConfigurationException, SAXException, IOException;//вместо load-get

    public void deleteMaterial(Integer ID) throws IOException, SAXException, ParserConfigurationException, TransformerException;
}
