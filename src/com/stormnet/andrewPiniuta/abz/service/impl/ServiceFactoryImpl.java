package com.stormnet.andrewPiniuta.abz.service.impl;

import com.stormnet.andrewPiniuta.abz.service.*;

//единственная конкретная фабрика для Service которая возвращвет объекты других классов

public class ServiceFactoryImpl extends ServiceFactory {

//переопределяем методы абстрактного класса
    @Override
    public MaterialService getMaterialService() {//возвращвет объекты по типу интерфейса
        return new MaterialServiceImpl();//для Material
    }

    @Override
    public RecipeService getRecipeService() {//возвращвет объекты по типу интерфейса
        return new RecipeServiceImpl();//для Recipe
    }

    @Override
    public ProductService getProductService() {
        return new ProductServiceImpl();
    }

    @Override
    public StaffService getStaffService() {
        return new StaffServiceImpl();
    }

    @Override
    public TruckService getTruckService() {
        return new TruckServiceImpl();
    }
}
