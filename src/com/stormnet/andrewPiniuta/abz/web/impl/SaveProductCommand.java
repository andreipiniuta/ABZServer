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

public class SaveProductCommand implements Command {


    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {
        Integer ID;
        if (request.getParameter("ID") == null){
            ID = null;
        } else {
            ID = (int) request.getParameter("ID");
        }

        String productName = request.getParameter("productName").toString();
        double productAmount = ((Number)request.getParameter("productAmount")).doubleValue();
        String constructionAddress = request.getParameter("constructionAddress").toString();

        Product product = new Product();
        product.setID(ID);
        product.setProductName(productName);
        product.setProductAmount(productAmount);
        product.setConstructionAddress(constructionAddress);

        ServiceFactory sf = ServiceFactory.getServiceFactory();
        ProductService ps = sf.getProductService();
        ps.saveProduct(product);

    }
}
