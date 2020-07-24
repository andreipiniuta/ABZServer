package com.stormnet.andrewPiniuta.abz.DAO.json;

import com.stormnet.andrewPiniuta.abz.DAO.*;


public class JSONDAOFactory extends DaoFactory {//класс конкретной фабрики, которая возвращвет объекты других классов
//возвращает конкретные объекты по типу интерфейса
    @Override
    public MaterialDao getMaterialDao() {
        return null;
    }

    @Override
    public RecipeDao getRecipeDao() {
        return null;
    }

    @Override
    public ProductDao getProductDao() {
        return null;
    }

    @Override
    public StaffDao getStaffDao() {
        return null;
    }

    @Override
    public TruckDao getTruckDao() {
        return null;
    }
}
