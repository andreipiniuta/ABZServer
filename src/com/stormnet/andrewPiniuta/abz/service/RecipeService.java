package com.stormnet.andrewPiniuta.abz.service;


import com.stormnet.andrewPiniuta.abz.data.Recipe;
import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public interface RecipeService {

    public  void saveRecipe(Recipe recipe) throws ParserConfigurationException, TransformerException, SAXException, IOException;

    List<Recipe> getAllRecipe() throws IOException, SAXException, ParserConfigurationException;

    Recipe getRecipeByID(Integer ID) throws ParserConfigurationException, SAXException, IOException;

    public void deleteRecipe(Integer ID) throws SAXException, TransformerException, ParserConfigurationException, IOException;
}
