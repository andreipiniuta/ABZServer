package com.stormnet.andrewPiniuta.abz.db;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

//класс по типу синглтон
public class MyIDGenerator {
    private static final MyIDGenerator generator = new MyIDGenerator(); //п1 создали объект Singletona

    public static MyIDGenerator getGenerator() { //п2 метод возвр сам объект Singletona
        return generator;
    }

    private MyIDGenerator() { //п.3 Приватный конструктор
    }


    public Integer getNextIdForMaterial() throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = null;
        try {
            document = dBuilder.parse("D:/stormnet-tasks/project/Material.xml");
        } catch (IOException | SAXException e) {
            throw new XmlException(e);
        }
        Element rootTag = document.getDocumentElement();//прочитал в rootTag корневой элемент
        NodeList idTagList = rootTag.getElementsByTagName("ID"); // в список idTagList из корневого Тэга получаем все тзги с названием ID(обычно тзги с назвю объекта)
//ищем в списке idTagList макмимальный ID
        int newId = 0;
        for (int i = 0; i < idTagList.getLength(); i++) {
            Node idTag = idTagList.item(i);// при каждой итерации в перемен idTag закидываю знначение ID из нашего списка
            String idStr = idTag.getTextContent();//перевод в строку
            int id = Integer.parseInt(idStr);// перевод в число

            if (id > newId) {
                newId = id;
            }
        }
        newId++;
        return newId;
    }

    public Integer getNextIdForRecipe() throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = null;
        try {
            document = dBuilder.parse("D:/stormnet-tasks/project/Recipe.xml");
        } catch (IOException | SAXException e) {
            throw new XmlException(e);
        }
        Element rootTag = document.getDocumentElement();//прочитал в rootTag корневой элемент
        NodeList idTagList = rootTag.getElementsByTagName("ID"); // в список idTagList из корневого Тэга получаем все тзги с названием ID(обычно тзги с назвю объекта)
//ищем в списке idTagList макмимальный ID
        int newId = 0;
        for (int i = 0; i < idTagList.getLength(); i++) {
            Node idTag = idTagList.item(i);// при каждой итерации в перемен idTag закидываю знначение ID из нашего списка
            String idStr = idTag.getTextContent();//перевод в строку
            int id = Integer.parseInt(idStr);// перевод в число

            if (id > newId) {
                newId = id;
            }
        }
        newId++;
        return newId;
    }

    public Integer getNextIdForProduct() throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = null;
        try {
            document = dBuilder.parse("D:/stormnet-tasks/project/Product.xml");
        } catch (IOException | SAXException e) {
            throw new XmlException(e);
        }
        Element rootTag = document.getDocumentElement();//прочитал в rootTag корневой элемент
        NodeList idTagList = rootTag.getElementsByTagName("ID"); // в список idTagList из корневого Тэга получаем все тзги с названием ID(обычно тзги с назвю объекта)
//ищем в списке idTagList макмимальный ID
        int newId = 0;
        for (int i = 0; i < idTagList.getLength(); i++) {
            Node idTag = idTagList.item(i);// при каждой итерации в перемен idTag закидываю знначение ID из нашего списка
            String idStr = idTag.getTextContent();//перевод в строку
            int id = Integer.parseInt(idStr);// перевод в число

            if (id > newId) {
                newId = id;
            }
        }
        newId++;
        return newId;
    }

    public Integer getNextIdForStaff() throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = null;
        try {
            document = dBuilder.parse("D:/stormnet-tasks/project/Staff.xml");
        } catch (IOException | SAXException e) {
            throw new XmlException(e);
        }
        Element rootTag = document.getDocumentElement();//прочитал в rootTag корневой элемент
        NodeList idTagList = rootTag.getElementsByTagName("ID"); // в список idTagList из корневого Тэга получаем все тзги с названием ID(обычно тзги с назвю объекта)
//ищем в списке idTagList макмимальный ID
        int newId = 0;
        for (int i = 0; i < idTagList.getLength(); i++) {
            Node idTag = idTagList.item(i);// при каждой итерации в перемен idTag закидываю знначение ID из нашего списка
            String idStr = idTag.getTextContent();//перевод в строку
            int id = Integer.parseInt(idStr);// перевод в число

            if (id > newId) {
                newId = id;
            }
        }
        newId++;
        return newId;
    }

    public Integer getNextIdForTruck() throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = null;
        try {
            document = dBuilder.parse("D:/stormnet-tasks/project/Truck.xml");
        } catch (IOException | SAXException e) {
            throw new XmlException(e);
        }
        Element rootTag = document.getDocumentElement();//прочитал в rootTag корневой элемент
        NodeList idTagList = rootTag.getElementsByTagName("ID"); // в список idTagList из корневого Тэга получаем все тзги с названием ID(обычно тзги с назвю объекта)
//ищем в списке idTagList макмимальный ID
        int newId = 0;
        for (int i = 0; i < idTagList.getLength(); i++) {
            Node idTag = idTagList.item(i);// при каждой итерации в перемен idTag закидываю знначение ID из нашего списка
            String idStr = idTag.getTextContent();//перевод в строку
            int id = Integer.parseInt(idStr);// перевод в число

            if (id > newId) {
                newId = id;
            }
        }
        newId++;
        return newId;
    }
}
