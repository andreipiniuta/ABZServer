package com.stormnet.andrewPiniuta.abz.service;


import com.stormnet.andrewPiniuta.abz.service.impl.ServiceFactoryImpl;


public abstract class ServiceFactory {

    public abstract MaterialService getMaterialService(); // абстрактные методы по интерфайсам

    public abstract RecipeService getRecipeService();// абстрактные методы по интерфайсам

    public abstract ProductService getProductService();// абстрактные методы по интерфайсам

    public abstract StaffService getStaffService();// абстрактные методы по интерфайсам

    public abstract TruckService getTruckService();// абстрактные методы по интерфайсам

    public static ServiceFactory getServiceFactory() {// метод, кот возвращает конкретную фабрику(для Service она одна)
        return new ServiceFactoryImpl();
    }
}
