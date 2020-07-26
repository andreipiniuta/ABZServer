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
import java.util.List;
import java.util.Map;

public class GetAllRecipeCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        RecipeService rs = sf.getRecipeService();

        List<Recipe> allRecipe = rs.getAllRecipe();
        //запись объекта через карту
        for (Recipe recipe : allRecipe) {
            Map<String, Object> recipeMap = new HashMap<>();
            recipeMap.put("ID", recipe.getID());
            recipeMap.put("productName", recipe.getProductName());
            recipeMap.put("sandPercent", recipe.getSandPercent());
            recipeMap.put("gravelPercent", recipe.getGravelPercent());
            recipeMap.put("bitumPercent", recipe.getBitumPercent());
            //каждую карту кладём в response
            response.addResponseData(recipeMap);
        }
    }
}
