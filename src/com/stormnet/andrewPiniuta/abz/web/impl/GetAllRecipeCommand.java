package com.stormnet.andrewPiniuta.abz.web.impl;

import com.stormnet.andrewPiniuta.abz.data.Recipe;
import com.stormnet.andrewPiniuta.abz.service.RecipeService;
import com.stormnet.andrewPiniuta.abz.service.ServiceFactory;
import com.stormnet.andrewPiniuta.abz.web.Command;
import com.stormnet.andrewPiniuta.abz.web.Request;
import com.stormnet.andrewPiniuta.abz.web.Response;
import org.json.JSONWriter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class GetAllRecipeCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        RecipeService rs = sf.getRecipeService();

        List<Recipe> allRecipe = rs.getAllRecipe();;

        JSONWriter jsonWriter = response.getJsonWriter();

        jsonWriter.array();
//из списка с объектами берем по одному и пихаем в json
        for (Recipe recipe : allRecipe) {

            jsonWriter.object();
            jsonWriter.key("ID").value(recipe.getID());
            jsonWriter.key("productName").value(recipe.getProductName());
            jsonWriter.key("sandPercent").value(recipe.getSandPercent());
            jsonWriter.key("gravelPercent").value(recipe.getGravelPercent());
            jsonWriter.key("bitumPercent").value(recipe.getBitumPercent());
            jsonWriter.endObject();
        }

        jsonWriter.endArray();

    }
}
