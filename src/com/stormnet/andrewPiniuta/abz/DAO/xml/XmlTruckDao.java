package com.stormnet.andrewPiniuta.abz.DAO.xml;

import com.stormnet.andrewPiniuta.abz.DAO.TruckDao;
import com.stormnet.andrewPiniuta.abz.data.Truck;
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

class XmlTruckDao implements TruckDao { //обл видимость пекета, чтобы было через фабрику DAO
    @Override
    public void saveTruck(Truck truck) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Integer truckID = truck.getID();
        if (truckID != null){
            throw new RuntimeException("Truck with " + truckID + "has already existed");
        }

        truckID = MyIDGenerator.getGenerator().getNextIdForTruck();
        truck.setID(truckID);
//читаем до корневого тзга
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("D:/stormnet-tasks/project/Truck.xml");
        Element rootTag = doc.getDocumentElement();
   // пишем в корневой

        Element truckTag = doc.createElement("truck");

        Element IDTag = doc.createElement("ID");
        IDTag.setTextContent(truck.getID().toString());

        Element truckTypeTag = doc.createElement("truckType");
        truckTypeTag.setTextContent(truck.getTruckType());

        Element truckNumberTag = doc.createElement("truckNumber");
        truckNumberTag.setTextContent(truck.getTruckNumber());

        Element payloadTag = doc.createElement("payload");
        payloadTag.setTextContent(Double.toString(truck.getPayload()));

        truckTag.appendChild(IDTag);
        truckTag.appendChild(truckTypeTag);
        truckTag.appendChild(truckNumberTag);
        truckTag.appendChild(payloadTag);
        rootTag.appendChild(truckTag);

        //запись заполненного дерева в файл
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(doc);
        StreamResult streamResult = new StreamResult("D:/stormnet-tasks/project/Truck.xml");

        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        transformer.transform(domSource, streamResult);

    }

    @Override
    public List<Truck> loadAllTruck() throws ParserConfigurationException, IOException, SAXException {
        List <Truck> storage = new ArrayList<>();
        //чтение существующего XML
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse("D:/stormnet-tasks/project/Truck.xml");
        Element rootTag = document.getDocumentElement();
        NodeList allTruckNodes = rootTag.getElementsByTagName("truck");
        int truckTagCount = allTruckNodes.getLength();
        for (int i = 0; i < truckTagCount; i++) {
            Truck truck = new Truck();
            Node truckNode = allTruckNodes.item(i);
            Element truckTag = (Element) truckNode;

            Element truckIDTag = (Element) truckTag.getElementsByTagName("ID").item(0);
            String truckIDStr = truckIDTag.getTextContent();
            Integer ID = Integer.parseInt(truckIDStr);
            truck.setID(ID);

            Element truckTypeTag = (Element) truckTag.getElementsByTagName("truckType").item(0);
            String truckType = truckTypeTag.getTextContent();
            truck.setTruckType(truckType);

            Element truckNumberTag = (Element) truckTag.getElementsByTagName("truckNumber").item(0);
            String truckNumber = truckNumberTag.getTextContent();
            truck.setTruckNumber(truckNumber);

            Element payloadTag = (Element) truckTag.getElementsByTagName("payload").item(0);
            String payloadStr = payloadTag.getTextContent();
            double payload = Double.parseDouble(payloadStr);
            truck.setPayload(payload);

            storage.add(truck);
        }

        return storage;
    }

    @Override
    public Truck loadTruckByID(Integer ID) throws IOException, SAXException, ParserConfigurationException {
        List <Truck> storage = loadAllTruck();// загрузка всех материалов

        if (ID == null) {//если вообще нет ID
            throw new RuntimeException("ID can not be null");
        }
        for (Truck truck : storage) {//ищем в листе объект с заданным ID
            if (ID.equals(truck.getID())) {
                return truck;
            }
        }
        throw  new RuntimeException("Truck with " + ID + "not found");//передали не существующий iD
    }

    @Override
    public void updateTruck(Truck truck) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        Integer ID = truck.getID();//узнали номер у прешедшего объекта

        if (ID == null) {//если ID ytn, т.е. null, то объекта нет в хранилище, и это ошибка
            throw new RuntimeException("Truck with " + ID + "has not existed");
        }
        Truck updatedTruck = loadTruckByID(ID);//загрузка объекта с указанным номером
//установили этому объекту новые значения
      updatedTruck.setID(truck.getID());
      updatedTruck.setTruckType(truck.getTruckType());
      updatedTruck.setTruckNumber(truck.getTruckNumber());
      updatedTruck.setPayload(truck.getPayload());

        List <Truck> storage = loadAllTruck(); //загрузка все объекты из хранилища

        for (Truck trck: storage) {
            if (trck.getID().equals(truck.getID())){//если элемент с ID из загружен хранилища совпадает с ID элемента кот пришел в метод, то
                storage.remove(trck);// то такой элемент удаляем из  списка
                break;
            }
        }
        storage.add(updatedTruck);// в список добавляет измененный элемент

//удаляем старый xml
        File file = new File("D:/stormnet-tasks/project/Truck.xml");
        file.delete();

        //запись нового списка в новый xml-файл
        writeDataToHardDisk(storage);
    }

    @Override
    public void deleteTruck(Integer ID) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        List <Truck> storage = loadAllTruck();// загрузка всех материалов

        if (ID == null) {//если вообще нет ID
            throw new RuntimeException("ID can not be null");
        }

        int storageSize = storage.size();
        for (Truck truck : storage) {//ищем в листе объект с заданным ID
            if (ID.equals(truck.getID())) {//если по номеру нашли
                storage.remove(truck);//удаление из листа
                break;
            }
            storageSize--;
        }
        if (storageSize == 0) {
            throw  new RuntimeException("Truck with " + ID + "not found");//передали не существующий iD
        }
//удаляем старый xml
        File file = new File("D:/stormnet-tasks/project/Truck.xml");
        file.delete();
//запись нового списка в новый xml-файл
        writeDataToHardDisk(storage);
    }



    public void writeDataToHardDisk(List<Truck> storage) throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element rootTag = document.createElement("truck-db");//пустой document типа Document создает корневой каталаг(имя каталога)
        for (Truck newTruck: storage) {
            Element truckTag = document.createElement("truck");//тэг-объект(имя)

            Element IDTag = document.createElement("ID");//тэг-поле(имя)
            IDTag.setTextContent(newTruck.getID().toString());

            Element truckTypeTag = document.createElement("truckType");//тэг-поле(имя)
            truckTypeTag.setTextContent(newTruck.getTruckType());

            Element truckNumberTag = document.createElement("truckNumber");
            truckNumberTag.setTextContent(newTruck.getTruckNumber());

            Element payloadTag = document.createElement("payload");
            payloadTag.setTextContent(Double.toString(newTruck.getPayload()));

            truckTag.appendChild(IDTag);
            truckTag.appendChild(truckTypeTag);
            truckTag.appendChild(truckNumberTag);
            truckTag.appendChild(payloadTag);

            rootTag.appendChild(truckTag);

        }
        document.appendChild(rootTag);

        //запись заполненного дерева в файл
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult("D:/stormnet-tasks/project/Truck.xml");

        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        transformer.transform(domSource, streamResult);
    }
}
