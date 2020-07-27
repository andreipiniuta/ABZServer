package com.stormnet.andrewPiniuta.abz.web.impl;


import com.stormnet.andrewPiniuta.abz.data.Recipe;
import com.stormnet.andrewPiniuta.abz.service.RecipeService;
import com.stormnet.andrewPiniuta.abz.service.ServiceFactory;
import com.stormnet.andrewPiniuta.abz.web.Command;
import com.stormnet.andrewPiniuta.abz.web.Request;
import com.stormnet.andrewPiniuta.abz.web.Response;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class SaveRecipeCommand implements Command {

    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {
        Integer ID;
        if (request.getParameter("ID") == null){
            ID = null;
        } else {
            ID = (int) request.getParameter("ID");
        }

        String productName = request.getParameter("productName").toString();
        double sandPercent = ((Number) request.getParameter("sandPercent")).doubleValue();
        double gravelPercent = ((Number)request.getParameter("gravelPercent")).doubleValue();
        double bitumPercent = ((Number)request.getParameter("bitumPercent")).doubleValue();

        Recipe recipe = new Recipe();
        recipe.setID(ID);
        recipe.setProductName(productName);
        recipe.setSandPercent(sandPercent);
        recipe.setGravelPercent(gravelPercent);
        recipe.setBitumPercent(bitumPercent);

        ServiceFactory sf = ServiceFactory.getServiceFactory();
        RecipeService rs = sf.getRecipeService();
        rs.saveRecipe(recipe);
    }
}
