package com.stormnet.andrewPiniuta.abz.DAO.xml;

import com.stormnet.andrewPiniuta.abz.DAO.*;


public class XmlDaoFactory extends DaoFactory {//класс конкретной фабрики, которая возвращвет объекты других классов
    //возвращает конкретные объекты по типу интерфейса
    @Override
    public MaterialDao getMaterialDao() {//возвращвет объекты по типу интерфейса
        return new XmlMaterialDao();//для Material
    }

    @Override
    public RecipeDao getRecipeDao() {//возвращвет объекты по типу интерфейса
        return new XmlRecipeDao();//для Recipe
    }

    @Override
    public ProductDao getProductDao() {
        return new XmlProductDao();
    }

    @Override
    public StaffDao getStaffDao() {
        return new XmlStaffDao();
    }

    @Override
    public TruckDao getTruckDao() {
        return new XmlTruckDao();
    }


}
