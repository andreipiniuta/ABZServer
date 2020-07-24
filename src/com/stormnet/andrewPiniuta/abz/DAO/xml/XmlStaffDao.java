package com.stormnet.andrewPiniuta.abz.DAO.xml;

import com.stormnet.andrewPiniuta.abz.DAO.StaffDao;
import com.stormnet.andrewPiniuta.abz.data.Staff;
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

class XmlStaffDao implements StaffDao { //обл видимость пекета, чтобы было через фабрику DAO
    @Override
    public void saveStaff(Staff staff) throws TransformerException, ParserConfigurationException, IOException, SAXException {
//установка ID новому материалу
        Integer staffID = staff.getID();// узнаем номер у объекта
        if (staffID != null) {//если ID уже есть, т.е. НЕ null, то такой объект уже есть, и это ошибка
            throw new RuntimeException("Staff member with " + staffID + "has already existed");
        }
        staffID = MyIDGenerator.getGenerator().getNextIdForStaff();//присваиваем ID-шник с пом класса-генератора ID
        staff.setID(staffID); //установка объекту ID-шника

//чтение существующего XML только до корневого Тэга
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse("D:/stormnet-tasks/project/Staff.xml");
        Element rootTag = document.getDocumentElement();

        //если надо--удаление прочитанного файла
//        File file = new File("D:/stormnet-tasks/project/materialFromClient.xml");
//        file.delete();

//запись(добавление) нового материала в прочитанный корневой Тэг
        Element recipeTag = document.createElement("staff");//тэг-объект(имя)

        Element IDTag = document.createElement("ID");//тэг-поле(имя)
        IDTag.setTextContent(staff.getID().toString());

        Element nameTag = document.createElement("name");//тэг-поле(имя)
        nameTag.setTextContent(staff.getName());

        Element surnameTag = document.createElement("surname");
        surnameTag.setTextContent(staff.getSurname());

        Element positionTag = document.createElement("position");
        positionTag.setTextContent(staff.getPosition());

        Element salaryTag = document.createElement("salary");
        salaryTag.setTextContent(Double.toString(staff.getSalary()));

        recipeTag.appendChild(IDTag);
        recipeTag.appendChild(nameTag);
        recipeTag.appendChild(surnameTag);
        recipeTag.appendChild(positionTag);
        recipeTag.appendChild(salaryTag);
        rootTag.appendChild(recipeTag);

        //запись заполненного дерева в файл
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult("D:/stormnet-tasks/project/Staff.xml");

        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        transformer.transform(domSource, streamResult);
    }

    @Override
    public List<Staff> loadAllStaff() throws ParserConfigurationException, IOException, SAXException {
        List <Staff> storage = new ArrayList<>();
        //чтение существующего XML
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse("D:/stormnet-tasks/project/Staff.xml");
        Element rootTag = document.getDocumentElement();
        NodeList allStaffNodes = rootTag.getElementsByTagName("staff");
        int staffTagCount = allStaffNodes.getLength();
        for (int i = 0; i < staffTagCount; i++) {
            Staff staff = new Staff();
            Node staffNode = allStaffNodes.item(i);
            Element staffTag = (Element) staffNode;

            Element staffIDTag = (Element) staffTag.getElementsByTagName("ID").item(0);
            String staffIDStr = staffIDTag.getTextContent();
            Integer ID = Integer.parseInt(staffIDStr);
            staff.setID(ID);

            Element nameTag = (Element) staffTag.getElementsByTagName("name").item(0);
            String name = nameTag.getTextContent();
            staff.setName(name);

            Element surnameTag = (Element) staffTag.getElementsByTagName("surname").item(0);
            String surname = surnameTag.getTextContent();
            staff.setSurname(surname);

            Element positionTag = (Element) staffTag.getElementsByTagName("position").item(0);
            String position = positionTag.getTextContent();
            staff.setPosition(position);

            Element salaryTag = (Element) staffTag.getElementsByTagName("salary").item(0);
            String salaryStr = salaryTag.getTextContent();
            double salary = Double.parseDouble(salaryStr);
            staff.setSalary(salary);

            storage.add(staff);
        }

        return storage;

    }

    @Override
    public Staff loadStaffByID(Integer ID) throws IOException, SAXException, ParserConfigurationException {
        List <Staff> storage = loadAllStaff();// загрузка всех материалов

        if (ID == null) {//если вообще нет ID
            throw new RuntimeException("ID can not be null");
        }
        for (Staff staff : storage) {//ищем в листе объект с заданным ID
            if (ID.equals(staff.getID())) {
                return staff;
            }
        }
        throw  new RuntimeException("Staff with " + ID + "not found");//передали не существующий iD
    }

    @Override
    public void updateStaff(Staff staff) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Integer ID = staff.getID();//узнали номер у прешедшего объекта

        if (ID == null) {//если ID ytn, т.е. null, то объекта нет в хранилище, и это ошибка
            throw new RuntimeException("Staff with " + ID + "has not existed");
        }
        Staff updatedStaff = loadStaffByID(ID);//загрузка объекта с указанным номером
//установили этому объекту новые значения
        updatedStaff.setID(staff.getID());
        updatedStaff.setName(staff.getName());
        updatedStaff.setSurname(staff.getSurname());
        updatedStaff.setPosition(staff.getPosition());
        updatedStaff.setSalary(staff.getSalary());

        List <Staff> storage = loadAllStaff(); //загрузка все объекты из хранилища

        for (Staff stf: storage) {
            if (stf.getID().equals(staff.getID())){//если элемент с ID из загружен хранилища совпадает с ID элемента кот пришел в метод, то
                storage.remove(stf);// то такой элемент удаляем из  списка
                break;
            }
        }
        storage.add(updatedStaff);// в список добавляет измененный элемент

//удаляем старый xml
        File file = new File("D:/stormnet-tasks/project/Staff.xml");
        file.delete();

        //запись нового списка в новый xml-файл
        writeDataToHardDisd(storage);


    }

    @Override
    public void deleteStaff(Integer ID) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        List <Staff> storage = loadAllStaff();// загрузка всех материалов

        if (ID == null) {//если вообще нет ID
            throw new RuntimeException("ID can not be null");
        }

        int storageSize = storage.size();
        for (Staff staff : storage) {//ищем в листе объект с заданным ID
            if (ID.equals(staff.getID())) {//если по номеру нашли
                storage.remove(staff);//удаление из листа
                break;
            }
            storageSize--;
        }
        if (storageSize == 0) {
            throw  new RuntimeException("Staff with " + ID + "not found");//передали не существующий iD
        }
//удаляем старый xml
        File file = new File("D:/stormnet-tasks/project/Staff.xml");
        file.delete();
//запись нового списка в новый xml-файл
        writeDataToHardDisd(storage);
    }

    public void writeDataToHardDisd(List <Staff> storage) throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element rootTag = document.createElement("staff-db");//пустой document типа Document создает корневой каталаг(имя каталога)
        for (Staff newStaff: storage) {
            Element staffTag = document.createElement("staff");//тэг-объект(имя)

            Element IDTag = document.createElement("ID");//тэг-поле(имя)
            IDTag.setTextContent(newStaff.getID().toString());

            Element nameTag = document.createElement("name");//тэг-поле(имя)
            nameTag.setTextContent(newStaff.getName());

            Element surnameTag = document.createElement("surname");
            surnameTag.setTextContent(newStaff.getSurname());

            Element positionTag = document.createElement("position");
            positionTag.setTextContent(newStaff.getPosition());

            Element salaryTag = document.createElement("salary");
            salaryTag.setTextContent(Double.toString(newStaff.getSalary()));

            staffTag.appendChild(IDTag);
            staffTag.appendChild(nameTag);
            staffTag.appendChild(surnameTag);
            staffTag.appendChild(positionTag);
            staffTag.appendChild(salaryTag);

            rootTag.appendChild(staffTag);

        }
        document.appendChild(rootTag);

        //запись заполненного дерева в файл
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult("D:/stormnet-tasks/project/Staff.xml");

        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        transformer.transform(domSource, streamResult);
    }
}
