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
import java.util.Map;

public class GetProductByIDCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {

        int ID = (int) request.getParameter("ID");
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        ProductService ps = sf.getProductService();
        Product product = ps.getProductByID(ID);

        //запись объекта через карту
        Map<String, Object> productMap = new HashMap<>();
//в карту кладем ключи и значения бизнес объекта
        productMap.put("ID", product.getID());
        productMap.put("productName", product.getProductName());
        productMap.put("productAmount", product.getProductAmount());
        productMap.put("constructionAddress", product.getConstructionAddress());
        //карту кладем в response
        response.addResponseData(productMap);
    }
}
