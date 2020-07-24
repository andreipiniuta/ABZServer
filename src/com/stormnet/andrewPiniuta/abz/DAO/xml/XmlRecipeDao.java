package com.stormnet.andrewPiniuta.abz.DAO.xml;

import com.stormnet.andrewPiniuta.abz.DAO.RecipeDao;
import com.stormnet.andrewPiniuta.abz.data.Recipe;
import com.stormnet.andrewPiniuta.abz.db.MyIDGenerator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class XmlRecipeDao implements RecipeDao { //обл видимость пекета, чтобы было через фабрику DAO

    @Override
    public void saveRecipe(Recipe recipe) throws ParserConfigurationException, IOException, SAXException, TransformerException {
//установка ID новому материалу
        Integer recipeID = recipe.getID();// узнаем номер у объекта
        if (recipeID != null) {//если ID уже есть, т.е. НЕ null, то такой объект уже есть, и это ошибка
            throw new RuntimeException("Recipe with " + recipeID + "has already existed");
        }
        recipeID = MyIDGenerator.getGenerator().getNextIdForRecipe();//присваиваем ID-шник с пом класса-генератора ID
        recipe.setID(recipeID); //установка объекту ID-шника

//чтение существующего XML только до корневого Тэга
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse("D:/stormnet-tasks/project/Recipe.xml");
        Element rootTag = document.getDocumentElement();

        //если надо--удаление прочитанного файла
//        File file = new File("D:/stormnet-tasks/project/materialFromClient.xml");
//        file.delete();

//запись(добавление) нового материала в прочитанный корневой Тэг
        Element recipeTag = document.createElement("recipe");//тэг-объект(имя)

        Element IDTag = document.createElement("ID");//тэг-поле(имя)
        IDTag.setTextContent(recipe.getID().toString());

        Element productNameTag = document.createElement("productName");//тэг-поле(имя)
        productNameTag.setTextContent(recipe.getProductName());

        Element sandPercentTag = document.createElement("sandPercent");
        sandPercentTag.setTextContent(Double.toString(recipe.getSandPercent()));

        Element gravelPercentTag = document.createElement("gravelPercent");
        gravelPercentTag.setTextContent(Double.toString(recipe.getGravelPercent()));

        Element bitumPercentTag = document.createElement("bitumPercent");
        bitumPercentTag.setTextContent(Double.toString(recipe.getBitumPercent()));

        recipeTag.appendChild(IDTag);
        recipeTag.appendChild(productNameTag);
        recipeTag.appendChild(sandPercentTag);
        recipeTag.appendChild(gravelPercentTag);
        recipeTag.appendChild(bitumPercentTag);
        rootTag.appendChild(recipeTag);

        //запись заполненного дерева в файл
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult("D:/stormnet-tasks/project/Recipe.xml");

        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        transformer.transform(domSource, streamResult);//объекту фабрики transformer даем команду transform(1. что писать, 2. куда писать)
    }


    @Override
    public List<Recipe> loadAllRecipe() throws ParserConfigurationException, IOException, SAXException {
        List <Recipe> storage = new ArrayList<>();
        //чтение существующего XML
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse("D:/stormnet-tasks/project/Recipe.xml");
        Element rootTag = document.getDocumentElement();
        NodeList allRecipeNodes = rootTag.getElementsByTagName("recipe");
        int recipeTagCount = allRecipeNodes.getLength();
        for (int i = 0; i < recipeTagCount; i++) {
            Recipe recipe = new Recipe();
            Node recipeNode = allRecipeNodes.item(i);
            Element recipeTag = (Element) recipeNode;

            Element recipeIDTag = (Element) recipeTag.getElementsByTagName("ID").item(0);
            String recipeIDStr = recipeIDTag.getTextContent();
            Integer ID = Integer.parseInt(recipeIDStr);
            recipe.setID(ID);

            Element productNameTag = (Element) recipeTag.getElementsByTagName("productName").item(0);
            String productName = productNameTag.getTextContent();
            recipe.setProductName(productName);

            Element sandPercentTag = (Element) recipeTag.getElementsByTagName("sandPercent").item(0);
            String sandPercentStr = sandPercentTag.getTextContent();
            double sandPercent = Double.parseDouble(sandPercentStr);
            recipe.setSandPercent(sandPercent);

            Element gravelPercentTag = (Element) recipeTag.getElementsByTagName("gravelPercent").item(0);
            String gravelPercentStr = gravelPercentTag.getTextContent();
            double gravelPercent = Double.parseDouble(gravelPercentStr);
            recipe.setGravelPercent(gravelPercent);

            Element bitumPercentTag = (Element) recipeTag.getElementsByTagName("bitumPercent").item(0);
            String bitumPercentStr = bitumPercentTag.getTextContent();
            double bitumPercent = Double.parseDouble(bitumPercentStr);
            recipe.setBitumPercent(bitumPercent);

            storage.add(recipe);
        }

        return storage;

    }

    @Override
    public Recipe loadRecipeByID(Integer ID) throws IOException, SAXException, ParserConfigurationException {
        List <Recipe> storage = loadAllRecipe();// загрузка всех материалов

        if (ID == null) {//если вообще нет ID
            throw new RuntimeException("ID can not be null");
        }
        for (Recipe recipe : storage) {//ищем в листе объект с заданным ID
            if (ID.equals(recipe.getID())) {
                return recipe;
            }
        }
        throw  new RuntimeException("Recipe with " + ID + "not found");//передали не существующий iD
    }

    @Override
    public void updateRecipe(Recipe recipe) throws ParserConfigurationException, SAXException, IOException, TransformerException {

        Integer ID = recipe.getID();//узнали номер у прешедшего объекта

        if (ID == null) {//если ID ytn, т.е. null, то объекта нет в хранилище, и это ошибка
            throw new RuntimeException("Recipe with " + ID + "has not existed");
        }
        Recipe updatedRecipe = loadRecipeByID(ID);//загрузка объекта с указанным номером
//установили этому объекту новые значения
        updatedRecipe.setID(recipe.getID());
        updatedRecipe.setProductName(recipe.getProductName());
        updatedRecipe.setSandPercent(recipe.getSandPercent());
        updatedRecipe.setGravelPercent(recipe.getGravelPercent());
        updatedRecipe.setBitumPercent(recipe.getBitumPercent());

        List <Recipe> storage = loadAllRecipe(); //загрузка все объекты из хранилища

        for (Recipe r: storage) {
            if (r.getID().equals(recipe.getID())){//если элемент с ID из загружен хранилища совпадает с ID элемента кот пришел в метод, то
                storage.remove(r);// то такой элемент удаляем из  списка
                break;
            }
        }
        storage.add(updatedRecipe);// в список добавляет измененный элемент

//удаляем старый xml
        File file = new File("D:/stormnet-tasks/project/Recipe.xml");
        file.delete();

        //запись нового списка в новый xml-файл
        writeDataToDisk(storage);
    }
    @Override
    public void deleteRecipe(Integer ID) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        List <Recipe> storage = loadAllRecipe();// загрузка всех материалов

        if (ID == null) {//если вообще нет ID
            throw new RuntimeException("ID can not be null");
        }

        int storageSize = storage.size();
        for (Recipe recipe : storage) {//ищем в листе объект с заданным ID
            if (ID.equals(recipe.getID())) {//если по номеру нашли
                storage.remove(recipe);//удаление из листа
                break;
            }
            storageSize--;
        }
        if (storageSize == 0) {
            throw  new RuntimeException("Recipe with " + ID + "not found");//передали не существующий iD
        }
//удаляем старый xml
        File file = new File("D:/stormnet-tasks/project/Recipe.xml");
        file.delete();
//запись нового списка в новый xml-файл
        writeDataToDisk(storage);
    }

    public void writeDataToDisk(List <Recipe> storage) throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element rootTag = document.createElement("recipe-db");//пустой document типа Document создает корневой каталаг(имя каталога)
        for (Recipe newRecipe: storage) {
            Element recipeTag = document.createElement("recipe");//тэг-объект(имя)

            Element IDTag = document.createElement("ID");//тэг-поле(имя)
            IDTag.setTextContent(newRecipe.getID().toString());

            Element productNameTag = document.createElement("productName");//тэг-поле(имя)
            productNameTag.setTextContent(newRecipe.getProductName());

            Element sandPercentTag = document.createElement("sandPercent");
            sandPercentTag.setTextContent(Double.toString(newRecipe.getSandPercent()));

            Element gravelPercentTag = document.createElement("gravelPercent");
            gravelPercentTag.setTextContent(Double.toString(newRecipe.getGravelPercent()));

            Element bitumPercentTag = document.createElement("bitumPercent");
            bitumPercentTag.setTextContent(Double.toString(newRecipe.getBitumPercent()));

            recipeTag.appendChild(IDTag);
            recipeTag.appendChild(productNameTag);
            recipeTag.appendChild(sandPercentTag);
            recipeTag.appendChild(gravelPercentTag);
            recipeTag.appendChild(bitumPercentTag);

            rootTag.appendChild(recipeTag);

        }
        document.appendChild(rootTag);

        //запись заполненного дерева в файл
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult("D:/stormnet-tasks/project/Recipe.xml");

        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        transformer.transform(domSource, streamResult);
    }
}
