package com.stormnet.andrewPiniuta.abz.DAO;


import com.stormnet.andrewPiniuta.abz.DAO.json.JSONDAOFactory;
import com.stormnet.andrewPiniuta.abz.DAO.xml.XmlDaoFactory;


public abstract class DaoFactory {//создал aбстрактную фабрику DAO объектов
    //методы, кот возвращают объекты(типа класса с реализацией методов) по типу интерфейса

    public abstract MaterialDao getMaterialDao();// абстрактные методы по интерфайсам

    public abstract RecipeDao getRecipeDao();//абстрактные методы по интерфайсам

    public abstract ProductDao getProductDao();

    public abstract StaffDao getStaffDao();

    public abstract TruckDao getTruckDao();


    public static DaoFactory getDaoFactory(EnumFactory ef) {// метод, кот возвращает конкретную фабрику(виды фабрик в Enum)
        switch (ef) {
            case XMLFactory:
                return new XmlDaoFactory();
            case JSONFactory:
                return new JSONDAOFactory();
        }
        return null;
    }
}
