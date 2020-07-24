package com.stormnet.andrewPiniuta.abz.service;


import com.stormnet.andrewPiniuta.abz.data.Product;
import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public interface ProductService {

    public  void saveProduct(Product product) throws ParserConfigurationException, TransformerException, SAXException, IOException;

    List<Product> getAllProduct() throws ParserConfigurationException, SAXException, IOException;

    Product getProductByID(Integer ID) throws IOException, SAXException, ParserConfigurationException;

    public void deleteProduct(Integer ID) throws ParserConfigurationException, TransformerException, SAXException, IOException;
}
