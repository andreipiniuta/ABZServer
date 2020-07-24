package com.stormnet.andrewPiniuta.abz.DAO;

import com.stormnet.andrewPiniuta.abz.data.Material;
import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public interface MaterialDao {

    public  void saveMaterial(Material material) throws IOException, ParserConfigurationException, SAXException, TransformerException;

    List<Material> loadAllMaterial() throws IOException, SAXException, ParserConfigurationException;

    Material loadMaterialByID(Integer ID) throws IOException, SAXException, ParserConfigurationException;

    public  void updateMaterial(Material material) throws ParserConfigurationException, SAXException, IOException, TransformerException;

    public void deleteMaterial(Integer ID) throws ParserConfigurationException, SAXException, IOException, TransformerException;

}
