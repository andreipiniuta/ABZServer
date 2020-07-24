package com.stormnet.andrewPiniuta.abz.DAO.json;

import com.stormnet.andrewPiniuta.abz.DAO.RecipeDao;
import com.stormnet.andrewPiniuta.abz.data.Recipe;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

//класс с реализацией методов DAO Interface
public class JsonRecipeDAO implements RecipeDao {

    @Override
    public void saveRecipe(Recipe recipe) throws ParserConfigurationException, IOException, SAXException, TransformerException {

    }

    @Override
    public List<Recipe> loadAllRecipe() {
        return null;
    }

    @Override
    public Recipe loadRecipeByID(Integer ID) {
        return null;
    }

    @Override
    public void updateRecipe(Recipe recipe) {

    }

    @Override
    public void deleteRecipe(Integer ID) {

    }
}
