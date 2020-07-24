package com.stormnet.andrewPiniuta.abz.DAO;

import com.stormnet.andrewPiniuta.abz.data.Product;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public interface ProductDao {
    public  void saveProduct(Product product) throws ParserConfigurationException, IOException, SAXException, TransformerException;

    List<Product> loadAllProduct() throws IOException, SAXException, ParserConfigurationException;

    Product loadProductByID(Integer ID) throws ParserConfigurationException, SAXException, IOException;

    public  void updateProduct(Product product) throws IOException, SAXException, ParserConfigurationException, TransformerException;

    public void deleteProduct(Integer ID) throws ParserConfigurationException, SAXException, IOException, TransformerException;
}
