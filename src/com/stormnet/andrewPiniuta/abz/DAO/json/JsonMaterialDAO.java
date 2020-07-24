package com.stormnet.andrewPiniuta.abz.DAO.json;

import com.stormnet.andrewPiniuta.abz.DAO.MaterialDao;
import com.stormnet.andrewPiniuta.abz.data.Material;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

//класс с реализацией методов DAO Interface
class JsonMaterialDAO implements MaterialDao {
    @Override
    public void saveMaterial(Material material) throws IOException, ParserConfigurationException, SAXException, TransformerException {

    }

    @Override
    public List<Material> loadAllMaterial() throws IOException, SAXException, ParserConfigurationException {
        return null;
    }

    @Override
    public Material loadMaterialByID(Integer ID) throws IOException, SAXException, ParserConfigurationException {
        return null;
    }

    @Override
    public void updateMaterial(Material material) throws ParserConfigurationException, SAXException, IOException {

    }

    @Override
    public void deleteMaterial(Integer ID) throws ParserConfigurationException, SAXException, IOException {

    } //обл видимость пакета, чтобы было через фабрику DAO

}
