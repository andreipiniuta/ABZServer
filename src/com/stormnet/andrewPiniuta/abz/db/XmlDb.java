package com.stormnet.andrewPiniuta.abz.db;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XmlDb {
    private static final String DB_ROOT_FOLDER = "D:/stormnet-tasks/project";

    private static final XmlDb db = new XmlDb();

    private Map<FileDataBase, Document> xmlDbDocuments;

    public static XmlDb getDb() {
        return db;
    }

    private XmlDb() {
        xmlDbDocuments = new HashMap<>();
    }

    private File getXmlFile(FileDataBase fileDataBase) {
        String xmlFileName = DB_ROOT_FOLDER + File.separator + fileDataBase.getFileName();
        File xmlFile = new File(xmlFileName);
        if (xmlFile.exists()) {
            try {
                xmlFile.createNewFile();
            } catch (IOException e) {
                throw new XmlException(e);
            }
        }
        return xmlFile;
    }

    private Document loadDocument(FileDataBase fileDataBase) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            File xmlFile = getXmlFile(fileDataBase);
            Document document = dBuilder.parse(xmlFile);

            return document;
        } catch (Exception e) {
            throw new XmlException(e);
        }
    }

    public Document getXmlDocument(FileDataBase fileDataBase) {
        Document document = xmlDbDocuments.get(fileDataBase);
        if (document == null) {
            document = loadDocument(fileDataBase);
            xmlDbDocuments.put(fileDataBase, document);
        }

        return document;
    }

    public Integer getNextId(FileDataBase fileDataBase) {
        Document document = getXmlDocument(fileDataBase);
        NodeList idTagList = document.getElementsByTagName("ID");

        int maxId = 0;
        for (int i = 0; i < idTagList.getLength(); i++) {
            Node idTag = idTagList.item(i);
            String idStr = idTag.getTextContent();
            int nextId = Integer.parseInt(idStr);

            if (nextId > maxId) {
                maxId = nextId;
            }
        }
        maxId++;
        return maxId;
    }


//    public void saveDocument(FileDataBase fileDataBase) {
//        File xmlFile = getXmlFile(fileDataBase);
//        xmlFile.delete();
//
//        try {
//            xmlFile.createNewFile();
//        } catch (IOException e) {
//            throw new XmlException(e);
//        }
//
//        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//        Document document = getXmlDocument(fileDataBase);
//        try {
//            Transformer transformer = transformerFactory.newTransformer();
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            DOMSource domSource = new DOMSource(document);
//            StreamResult streamResult = new StreamResult(xmlFile);
//            transformer.transform(domSource, streamResult);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public void updateDocument(Integer idForUpdate, String rowName, Element updatedObject, FileDataBase fileDataBase) {
//        Document document = getXmlDocument(fileDataBase);
//        NodeList objectTagList = document.getElementsByTagName(rowName);
//        Element oldObjectTag = null;
//
//        for (int i = 0; i < objectTagList.getLength(); i++) {
//            Element objectTag = (Element)objectTagList.item(i);
//
//            String idStr = objectTag.getElementsByTagName("id").item(0).getTextContent();
//            Integer id = Integer.valueOf(idStr);
//
//            if (id.equals(idForUpdate)) {
//                oldObjectTag = objectTag;
//                break;
//            }
//        }
//
//        if (oldObjectTag != null) {
//            document.getDocumentElement().removeChild(oldObjectTag);
//            document.getDocumentElement().appendChild(updatedObject);
//
//            saveDocument(fileDataBase);
//        }
//    }

//    public void deleteFromDocument(Integer idForDelete, String rowName, FileDataBase fileDataBase) {
//        Document document = getXmlDocument(fileDataBase);
//        NodeList objectTagList = document.getElementsByTagName(rowName);
//        Element oldObjectTag = null;
//
//        for (int i = 0; i < objectTagList.getLength(); i++) {
//            Element objectTag = (Element)objectTagList.item(i);
//
//            String idStr = objectTag.getElementsByTagName("id").item(0).getTextContent();
//            Integer id = Integer.valueOf(idStr);
//
//            if (id.equals(idForDelete)) {
//                oldObjectTag = objectTag;
//                break;
//            }
//        }
//
//        if (oldObjectTag != null) {
//            document.getDocumentElement().removeChild(oldObjectTag);
//            saveDocument(fileDataBase);
//        }
//    }
}
