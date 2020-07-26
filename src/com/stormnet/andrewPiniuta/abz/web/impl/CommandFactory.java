package com.stormnet.andrewPiniuta.abz.web.impl;

import com.stormnet.andrewPiniuta.abz.web.Command;

import java.util.HashMap;
import java.util.Map;

//класс фабрика по созданию команд по типу сингтона(типа как фабрика окон)
//команда это метод, реализованный в классах impl
public class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();// создал объект синглтона

    private Map<String, Command> commands = new HashMap<>();//карты по созданию и хранению команд


    public static CommandFactory get() {//метод по возврату этого объекта сингтона
        return instance;
    }

    private CommandFactory() {//приватный конструктор, кладем в карту команды: название команды --сама команда
        commands.put("save-material", new SaveMaterialCommand());
        commands.put("get-all-material", new GetAllMaterialCommand());
        commands.put("get-material-by-id", new GetMaterialByIDCommand());
        commands.put("delete-material", new DeleteMaterialCommand());
        commands.put("save-recipe", new SaveRecipeCommand());
        commands.put("save-staff", new SaveStaffCommand());
        commands.put("save-product", new SaveProductCommand());
        commands.put("save-truck", new SaveTruckCommand());
        commands.put("delete-product", new DeleteProductCommand());
        commands.put("delete-recipe", new DeleteRecipeCommand());
        commands.put("delete-staff", new DeleteStaffCommand());
        commands.put("delete-truck", new DeleteTruckCommand());
        commands.put("get-all-recipe", new GetAllRecipeCommand());
        commands.put("get-all-product", new GetAllProductCommand());
        commands.put("get-all-staff", new GetAllStaffCommand());
        commands.put("get-all-truck", new GetAllTruckCommand());
        commands.put("get-recipe-by-id", new GetRecipeByIDCommand());
        commands.put("get-product-by-id", new GetProductByIDCommand());
        commands.put("get-staff-by-id", new GetStaffByIDCommand());
        commands.put("get-truck-by-id", new GetTruckByIDCommand());


    }

    public Command getCommand(String commandName) {//геттер для получения команды по имени
        Command command = commands.get(commandName);
        return command;
    }
}
