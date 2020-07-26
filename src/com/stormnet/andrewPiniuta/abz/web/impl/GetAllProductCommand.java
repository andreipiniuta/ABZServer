package com.stormnet.andrewPiniuta.abz.web.impl;

import com.stormnet.andrewPiniuta.abz.data.Product;
import com.stormnet.andrewPiniuta.abz.service.ProductService;
import com.stormnet.andrewPiniuta.abz.service.ServiceFactory;
import com.stormnet.andrewPiniuta.abz.web.Command;
import com.stormnet.andrewPiniuta.abz.web.Request;
import com.stormnet.andrewPiniuta.abz.web.Response;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllProductCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {

        ServiceFactory sf = ServiceFactory.getServiceFactory();
        ProductService ps = sf.getProductService();

        List<Product> allProduct = ps.getAllProduct();
        //запись объекта через карту
        for (Product product : allProduct) {
            Map<String, Object> productMap = new HashMap<>();
            productMap.put("ID", product.getID());
            productMap.put("productName", product.getProductName());
            productMap.put("productAmount", product.getProductAmount());
            productMap.put("constructionAddress", product.getConstructionAddress());
            //каждую карту кладём в response
            response.addResponseData(productMap);
        }
    }
}
