package com.stormnet.andrewPiniuta.abz.service.impl;

import com.stormnet.andrewPiniuta.abz.DAO.DaoFactory;
import com.stormnet.andrewPiniuta.abz.DAO.EnumFactory;
import com.stormnet.andrewPiniuta.abz.DAO.ProductDao;
import com.stormnet.andrewPiniuta.abz.DAO.RecipeDao;
import com.stormnet.andrewPiniuta.abz.data.Product;
import com.stormnet.andrewPiniuta.abz.service.ProductService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public void saveProduct(Product product) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
        ProductDao dao = df.getProductDao();
        Integer id = product.getID();
        if (id == null) {
            dao.saveProduct(product);
        } else {
            dao.updateProduct(product);
        }
    }

    @Override
    public List<Product> getAllProduct() throws ParserConfigurationException, SAXException, IOException {
        DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
        ProductDao dao = df.getProductDao();
        return dao.loadAllProduct();
    }

    @Override
    public Product getProductByID(Integer ID) throws IOException, SAXException, ParserConfigurationException {
        DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
        ProductDao dao = df.getProductDao();
        return dao.loadProductByID(ID);
    }

    @Override
    public void deleteProduct(Integer ID) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
        ProductDao dao = df.getProductDao();
        dao.deleteProduct(ID);

    }
}
