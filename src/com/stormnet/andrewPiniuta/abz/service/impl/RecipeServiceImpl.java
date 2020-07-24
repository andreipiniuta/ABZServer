package com.stormnet.andrewPiniuta.abz.service.impl;

import com.stormnet.andrewPiniuta.abz.DAO.DaoFactory;
import com.stormnet.andrewPiniuta.abz.DAO.EnumFactory;

import com.stormnet.andrewPiniuta.abz.DAO.RecipeDao;
import com.stormnet.andrewPiniuta.abz.data.Recipe;
import com.stormnet.andrewPiniuta.abz.service.RecipeService;
import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class RecipeServiceImpl implements RecipeService {

    @Override
    public void saveRecipe(Recipe recipe) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
        RecipeDao dao = df.getRecipeDao();
        Integer id = recipe.getID();
        if (id == null) {
            dao.saveRecipe(recipe);
        } else {
            dao.updateRecipe(recipe);
        }
    }

    @Override
    public List<Recipe> getAllRecipe() throws IOException, SAXException, ParserConfigurationException {
        DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
        RecipeDao dao = df.getRecipeDao();
        return dao.loadAllRecipe();
    }

    @Override
    public Recipe getRecipeByID(Integer ID) throws ParserConfigurationException, SAXException, IOException {
        DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
        RecipeDao dao = df.getRecipeDao();
        return dao.loadRecipeByID(ID);

    }

    @Override
    public void deleteRecipe(Integer ID) throws SAXException, TransformerException, ParserConfigurationException, IOException {

        DaoFactory df = DaoFactory.getDaoFactory(EnumFactory.XMLFactory);
        RecipeDao dao = df.getRecipeDao();
        dao.deleteRecipe(ID);

    }
}
