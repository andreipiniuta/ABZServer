package com.stormnet.andrewPiniuta.abz.DAO;

import com.stormnet.andrewPiniuta.abz.data.Material;
import com.stormnet.andrewPiniuta.abz.data.Recipe;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public interface RecipeDao {

    public  void saveRecipe(Recipe recipe) throws ParserConfigurationException, IOException, SAXException, TransformerException;

    List<Recipe> loadAllRecipe() throws ParserConfigurationException, IOException, SAXException;

    Recipe loadRecipeByID(Integer ID) throws IOException, SAXException, ParserConfigurationException;

    public  void updateRecipe(Recipe recipe) throws ParserConfigurationException, SAXException, IOException, TransformerException;

    public void deleteRecipe(Integer ID) throws IOException, SAXException, ParserConfigurationException, TransformerException;
}
