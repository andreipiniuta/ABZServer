package com.stormnet.andrewPiniuta.abz.DAO.xml;

import com.stormnet.andrewPiniuta.abz.DAO.ProductDao;
import com.stormnet.andrewPiniuta.abz.data.Product;
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

class XmlProductDao implements ProductDao { //обл видимость пекета, чтобы было через фабрику DAO
    @Override
    public void saveProduct(Product product) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Integer PrID = product.getID();
        if (PrID != null) {
            throw new RuntimeException("Product with " + PrID + "has already existed");
        }
        PrID = MyIDGenerator.getGenerator().getNextIdForProduct();
        product.setID(PrID);

        DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document documtnt = builder.parse("D:/stormnet-tasks/project/Product.xml");

        Element rootTag = documtnt.getDocumentElement();

//запись(добавление) нового материала в прочитанный корневой Тэг
        Element productTag = documtnt.createElement("product");

        Element IDTag = documtnt.createElement("ID");
        IDTag.setTextContent(product.getID().toString());

        Element productNameTag = documtnt.createElement("productName");
        productNameTag.setTextContent(product.getProductName());

        Element productAmount = documtnt.createElement("productAmount");
        productAmount.setTextContent(Double.toString(product.getProductAmount()));

        Element constructionAddress = documtnt.createElement("constructionAddress");
        constructionAddress.setTextContent(product.getConstructionAddress());

        productTag.appendChild(IDTag);
        productTag.appendChild(productNameTag);
        productTag.appendChild(productAmount);
        productTag.appendChild(constructionAddress);
        rootTag.appendChild(productTag);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(documtnt);
        StreamResult streamResult = new StreamResult("D:/stormnet-tasks/project/Product.xml");

        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        transformer.transform(domSource, streamResult);

    }

    @Override
    public List<Product> loadAllProduct() throws IOException, SAXException, ParserConfigurationException {

        List <Product> storage = new ArrayList<>();
        //чтение существующего XML
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse("D:/stormnet-tasks/project/Product.xml");
        Element rootTag = document.getDocumentElement();
        NodeList allProductNodes = rootTag.getElementsByTagName("product");
        int productTagCount = allProductNodes.getLength();
        for (int i = 0; i < productTagCount; i++) {
            Product product = new Product();
            Node productNode = allProductNodes.item(i);
            Element productTag = (Element) productNode;

            Element productIDTag = (Element) productTag.getElementsByTagName("ID").item(0);
            String productIDStr = productIDTag.getTextContent();
            Integer ID = Integer.parseInt(productIDStr);
            product.setID(ID);

            Element productNameTag = (Element) productTag.getElementsByTagName("productName").item(0);
            String productName = productNameTag.getTextContent();
            product.setProductName(productName);

            Element productAmountTag = (Element) productTag.getElementsByTagName("productAmount").item(0);
            String productAmountStr = productAmountTag.getTextContent();
            double productAmount = Double.parseDouble(productAmountStr);
            product.setProductAmount(productAmount);

            Element constructionAddressTag = (Element) productTag.getElementsByTagName("constructionAddress").item(0);
            String constructionAddress = constructionAddressTag.getTextContent();
            product.setConstructionAddress(constructionAddress);

            storage.add(product);
        }

        return storage;

    }

    @Override
    public Product loadProductByID(Integer ID) throws ParserConfigurationException, SAXException, IOException {
        List <Product> storage = loadAllProduct();// загрузка всех материалов

        if (ID == null) {//если вообще нет ID
            throw new RuntimeException("ID can not be null");
        }
        for (Product product : storage) {//ищем в листе объект с заданным ID
            if (ID.equals(product.getID())) {
                return product;
            }
        }
        throw  new RuntimeException("Product with " + ID + "not found");//передали не существующий iD
    }

    @Override
    public void updateProduct(Product product) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        Integer ID = product.getID();//узнали номер у прешедшего объекта

        if (ID == null) {//если ID ytn, т.е. null, то объекта нет в хранилище, и это ошибка
            throw new RuntimeException("Product with " + ID + "has not existed");
        }
        Product updatedProduct = loadProductByID(ID);//загрузка объекта с указанным номером
//установили этому объекту новые значения
        updatedProduct.setID(product.getID());
        updatedProduct.setProductName(product.getProductName());
        updatedProduct.setProductAmount(product.getProductAmount());
        updatedProduct.setConstructionAddress(product.getConstructionAddress());

        List <Product> storage = loadAllProduct(); //загрузка все объекты из хранилища

        for (Product prd: storage) {
            if (prd.getID().equals(product.getID())){//если элемент с ID из загружен хранилища совпадает с ID элемента кот пришел в метод, то
                storage.remove(prd);// то такой элемент удаляем из  списка
                break;
            }
        }
        storage.add(updatedProduct);// в список добавляет измененный элемент

//удаляем старый xml
        File file = new File("D:/stormnet-tasks/project/Product.xml");
        file.delete();

        //запись нового списка в новый xml-файл
        writeDataToDB(storage);

    }

    @Override
    public void deleteProduct(Integer ID) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        List <Product> storage = loadAllProduct();// загрузка всех материалов

        if (ID == null) {//если вообще нет ID
            throw new RuntimeException("ID can not be null");
        }

        int storageSize = storage.size();
        for (Product product : storage) {//ищем в листе объект с заданным ID
            if (ID.equals(product.getID())) {//если по номеру нашли
                storage.remove(product);//удаление из листа
                break;
            }
            storageSize--;
        }
        if (storageSize == 0) {
            throw  new RuntimeException("Product with " + ID + "not found");//передали не существующий iD
        }
//удаляем старый xml
        File file = new File("D:/stormnet-tasks/project/Product.xml");
        file.delete();
//запись нового списка в новый xml-файл
        writeDataToDB(storage);

    }

    public void writeDataToDB(List <Product> storage) throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element rootTag = document.createElement("product-db");//пустой document типа Document создает корневой каталаг(имя каталога)
        for (Product newProduct: storage) {
            Element productTag = document.createElement("product");//тэг-объект(имя)

            Element IDTag = document.createElement("ID");//тэг-поле(имя)
            IDTag.setTextContent(newProduct.getID().toString());

            Element productNameTag = document.createElement("productName");//тэг-поле(имя)
            productNameTag.setTextContent(newProduct.getProductName());

            Element productAmountTag = document.createElement("productAmount");
            productAmountTag.setTextContent(Double.toString(newProduct.getProductAmount()));

            Element constructionAddressTag = document.createElement("constructionAddress");
            constructionAddressTag.setTextContent(newProduct.getConstructionAddress());

            productTag.appendChild(IDTag);
            productTag.appendChild(productNameTag);
            productTag.appendChild(productAmountTag);
            productTag.appendChild(constructionAddressTag);

            rootTag.appendChild(productTag);

        }
        document.appendChild(rootTag);

        //запись заполненного дерева в файл
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult("D:/stormnet-tasks/project/Product.xml");

        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        transformer.transform(domSource, streamResult);
    }
}
