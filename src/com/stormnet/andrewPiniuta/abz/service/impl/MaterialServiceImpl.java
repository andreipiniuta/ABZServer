package com.stormnet.andrewPiniuta.abz.service.impl;

import com.stormnet.andrewPiniuta.abz.DAO.DaoFactory;
import com.stormnet.andrewPiniuta.abz.DAO.EnumFactory;
import com.stormnet.andrewPiniuta.abz.DAO.MaterialDao;
import com.stormnet.andrewPiniuta.abz.data.Material;
import com.stormnet.andrewPiniuta.abz.service.MaterialService;
import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

//класс с реализацией методов Servise Interface
public class MaterialServiceImpl implements MaterialService {
//общий принцип: создать dao нужный объект с пом фабрики и вызвать у него нужный метод
    @Override
    public void saveMaterial(Material material) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
        MaterialDao dao = df.getMaterialDao();
        Integer id = material.getID();
        if (id == null) {
            dao.saveMaterial(material);
        } else {
            dao.updateMaterial(material);
        }
    }

    @Override
    public List<Material> getAllMaterial() throws ParserConfigurationException, SAXException, IOException {
        DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
        MaterialDao dao = df.getMaterialDao();
        return dao.loadAllMaterial();
    }

    @Override
    public Material getMaterialbyID(Integer ID) throws ParserConfigurationException, SAXException, IOException {
        DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
        MaterialDao dao = df.getMaterialDao();
        return dao.loadMaterialByID(ID);
    }

    @Override
    public void deleteMaterial(Integer ID) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
        MaterialDao dao = df.getMaterialDao();
        dao.deleteMaterial(ID);
    }
}
