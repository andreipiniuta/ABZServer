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
import java.util.HashMap;
import java.util.Map;

public class GetRecipeByIDCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {
        int ID = (int) request.getParameter("ID");
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        RecipeService rs = sf.getRecipeService();
        Recipe recipe = rs.getRecipeByID(ID);

        //запись объекта через карту
        Map<String, Object> recipeMap = new HashMap<>();
//в карту кладем ключи и значения бизнес объекта
        recipeMap.put("ID", recipe.getID());
        recipeMap.put("productName", recipe.getProductName());
        recipeMap.put("sandPercent", recipe.getSandPercent());
        recipeMap.put("gravelPercent", recipe.getGravelPercent());
        recipeMap.put("bitumPercent", recipe.getBitumPercent());
        //карту кладем в response
        response.addResponseData(recipeMap);
    }
}
