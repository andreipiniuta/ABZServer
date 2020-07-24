package com.stormnet.andrewPiniuta.abz.web.impl;

import com.stormnet.andrewPiniuta.abz.data.Product;
import com.stormnet.andrewPiniuta.abz.service.ProductService;
import com.stormnet.andrewPiniuta.abz.service.ServiceFactory;
import com.stormnet.andrewPiniuta.abz.web.Command;
import com.stormnet.andrewPiniuta.abz.web.Request;
import com.stormnet.andrewPiniuta.abz.web.Response;
import org.json.JSONWriter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class GetProductByIDCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {

        int ID = (int) request.getParameter("ID");
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        ProductService ps = sf.getProductService();
        Product product = ps.getProductByID(ID);

        JSONWriter jsonWriter = response.getJsonWriter();
        jsonWriter.object();
        jsonWriter.key("ID").value(product.getID());
        jsonWriter.key("productName").value(product.getProductName());
        jsonWriter.key("productAmount").value(product.getProductAmount());
        jsonWriter.key("constructionAddress").value(product.getConstructionAddress());
        jsonWriter.endObject();
    }
}
