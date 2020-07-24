package com.stormnet.andrewPiniuta.abz.DAO.xml;

import com.stormnet.andrewPiniuta.abz.DAO.MaterialDao;
import com.stormnet.andrewPiniuta.abz.data.Material;
import com.stormnet.andrewPiniuta.abz.data.MaterialName;
import com.stormnet.andrewPiniuta.abz.data.ProviderName;
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

//класс с реализацией методов DAO Interface
class XmlMaterialDao implements MaterialDao { //обл видимость пакета, чтобы было через фабрику DAO

    @Override
    public void saveMaterial(Material material) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        //установка ID новому материалу
        Integer materialID = material.getID();// узнаем номер у объекта
        if (materialID != null) {//если ID уже есть, т.е. НЕ null, то такой объект уже есть, и это ошибка
            throw new RuntimeException("Material with " + materialID + "has already existed");
        }
        materialID = MyIDGenerator.getGenerator().getNextIdForMaterial();//присваиваем ID-шник с пом класса-генератора ID
        material.setID(materialID); //установка объекту ID-шника

//чтение существующего XML только до корневого Тэга
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse("D:/stormnet-tasks/project/Material.xml");
        Element rootTag = document.getDocumentElement();


//запись(добавление) нового материала в прочитанный корневой Тэг
        Element materialTag = document.createElement("material");//тэг-объект(имя)

        Element IDTag = document.createElement("ID");//тэг-поле(имя)
        IDTag.setTextContent(material.getID().toString());

        Element nameMaterialTag = document.createElement("materialName");//тэг-поле(имя)
        nameMaterialTag.setTextContent(material.getMaterialName().toString());//установка тэг-полю значения объекта

        Element providerNameTag = document.createElement("providerName");
        providerNameTag.setTextContent(material.getProviderName().toString());

        Element amountTag = document.createElement("amount");
        amountTag.setTextContent(Double.toString(material.getAmount()));

        Element costPerOneTag = document.createElement("costPerOne");
        costPerOneTag.setTextContent(Double.toString(material.getCostPerOne()));

        Element totalCostTag = document.createElement("totalCost");
        totalCostTag.setTextContent(Double.toString(material.getTotalCost()));

        materialTag.appendChild(IDTag);
        materialTag.appendChild(nameMaterialTag);
        materialTag.appendChild(providerNameTag);
        materialTag.appendChild(amountTag);
        materialTag.appendChild(costPerOneTag);
        materialTag.appendChild(totalCostTag);
        rootTag.appendChild(materialTag);

        //document.appendChild(rootTag)  этого нет т.к. корневой тзг уже есть в файле и его не надо добавлять

        // запись заполненного дерева в файл
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult("D:/stormnet-tasks/project/Material.xml");

        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        transformer.transform(domSource, streamResult);//объекту фабрики transformer даем команду transform(1. что писать, 2. куда писать)
    }
    @Override
    public List<Material> loadAllMaterial() throws IOException, SAXException, ParserConfigurationException {

        List <Material> storage = new ArrayList<>();

        //чтение существующего XML
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse("D:/stormnet-tasks/project/Material.xml");
        Element rootTag = document.getDocumentElement();
        NodeList allMaterialNodes = rootTag.getElementsByTagName("material");
        int materialTagCount = allMaterialNodes.getLength();
        for (int i = 0; i < materialTagCount; i++) {
            Material material = new Material();
            Node materialNode = allMaterialNodes.item(i);
            Element materialTag = (Element) materialNode;

            Element materialIDTag = (Element) materialTag.getElementsByTagName("ID").item(0);
            String materialIDStr = materialIDTag.getTextContent();
            Integer mID = Integer.parseInt(materialIDStr);
            material.setID(mID);

            Element materialNameTag = (Element) materialTag.getElementsByTagName("materialName").item(0);
            String materialNameStr = materialNameTag.getTextContent();
            MaterialName materialName = MaterialName.valueOf(materialNameStr);
            material.setMaterialName(materialName);

            Element providerNameTag = (Element) materialTag.getElementsByTagName("providerName").item(0);
            String providerNameStr = providerNameTag.getTextContent();
            ProviderName providerName = ProviderName.valueOf(providerNameStr);
            material.setProviderName(providerName);

            Element amountTag = (Element) materialTag.getElementsByTagName("amount").item(0);
            String amountStr = amountTag.getTextContent();
            double amount = Double.parseDouble(amountStr);
            material.setAmount(amount);

            Element costPerOneTag = (Element) materialTag.getElementsByTagName("costPerOne").item(0);// у
            String costPerOneStr = costPerOneTag.getTextContent();
            double costPerOne = Double.parseDouble(costPerOneStr);
            material.setCostPerOne(costPerOne);

            material.getTotalCost();

            storage.add(material);
        }
        return storage;
    }
    @Override
    public Material loadMaterialByID(Integer ID) throws IOException, SAXException, ParserConfigurationException {

        List <Material> storage = loadAllMaterial();// загрузка всех материалов

        if (ID == null) {//если вообще нет ID
            throw new RuntimeException("ID can not be null");
        }
        for (Material material : storage) {//ищем в листе объект с заданным ID
            if (ID.equals(material.getID())) {
                return material;
            }
        }
        throw  new RuntimeException("Material with " + ID + "not found");//передали не существующий iD
    }

    @Override
    public void updateMaterial(Material material) throws ParserConfigurationException, SAXException, IOException, TransformerException {//обновить объект
        Integer ID = material.getID();//узнали номер у прешедшего объекта

        if (ID == null) {//если ID нет, т.е. null, то объекта нет в хранилище, и это ошибка
            throw new RuntimeException("Material with " + ID + "has not existed");
        }
        Material updatedMaterial = loadMaterialByID(ID);//загрузка объекта с указанным номером
//установили этому объекту новые значения
        updatedMaterial.setID(material.getID());
        updatedMaterial.setMaterialName(material.getMaterialName());
        updatedMaterial.setProviderName(material.getProviderName());
        updatedMaterial.setAmount(material.getAmount());//установка этому объекту новое значение количество
        updatedMaterial.setCostPerOne(material.getCostPerOne());//установка этому объекту новое значение цена
        updatedMaterial.getTotalCost();

        List <Material> storage = loadAllMaterial(); //загрузка все объекты из хранилища

        for (Material m: storage) {
            if (m.getID().equals(material.getID())){//если элемент с ID из загружен хранилища совпадает с ID элемента кот пришел в метод, то
                storage.remove(m);// то такой элемент удаляем из  списка
                break;
            }
        }
        storage.add(updatedMaterial);// в список добавляет измененный элемент

//удаляем старый xml
        File file = new File("D:/stormnet-tasks/project/Material.xml");
        file.delete();
        //запись нового списка в новый xml-файл
        writeDataToDB(storage);
    }

    @Override
    public void deleteMaterial(Integer ID) throws ParserConfigurationException, SAXException, IOException, TransformerException {

        List <Material> storage = loadAllMaterial();// загрузка всех материалов

        if (ID == null) {//если вообще нет ID
            throw new RuntimeException("ID can not be null");
        }

        int storageSize = storage.size();
        for (Material material : storage) {//ищем в листе объект с заданным ID
            if (ID.equals(material.getID())) {//если по номеру нашли
                storage.remove(material);//удаление из листа
                break;
                }
            storageSize--;
        }
        if (storageSize == 0) {
            throw  new RuntimeException("Material with " + ID + "not found");//передали не существующий iD
        }
//удаляем старый xml
        File file = new File("D:/stormnet-tasks/project/Material.xml");
        file.delete();
//запись нового списка в новый xml-файл
        writeDataToDB(storage);
    }



    //запись нового списка в новый xml-файл
    public void writeDataToDB( List <Material> storage) throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element rootTag = document.createElement("material-db");//пустой document типа Document создает корневой каталаг(имя каталога)
        for (Material newMaterial: storage) {

            Element materialTag = document.createElement("material");//тэг-объект(имя)

            Element IDTag = document.createElement("ID");//тэг-поле(имя)
            IDTag.setTextContent(newMaterial.getID().toString());

            Element nameMaterialTag = document.createElement("materialName");//тэг-поле(имя)
            nameMaterialTag.setTextContent(newMaterial.getMaterialName().toString());//установка тэг-полю значения объекта

            Element providerNameTag = document.createElement("providerName");
            providerNameTag.setTextContent(newMaterial.getProviderName().toString());

            Element amountTag = document.createElement("amount");
            amountTag.setTextContent(Double.toString(newMaterial.getAmount()));

            Element costPerOneTag = document.createElement("costPerOne");
            costPerOneTag.setTextContent(Double.toString(newMaterial.getCostPerOne()));

            Element totalCostTag = document.createElement("totalCost");
            totalCostTag.setTextContent(Double.toString(newMaterial.getTotalCost()));

            materialTag.appendChild(IDTag);
            materialTag.appendChild(nameMaterialTag);
            materialTag.appendChild(providerNameTag);
            materialTag.appendChild(amountTag);
            materialTag.appendChild(costPerOneTag);
            materialTag.appendChild(totalCostTag);
            rootTag.appendChild(materialTag);

        }
        document.appendChild(rootTag);

        //запись заполненного дерева в файл
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult("D:/stormnet-tasks/project/Material.xml");

        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        transformer.transform(domSource, streamResult);//
    }

}
