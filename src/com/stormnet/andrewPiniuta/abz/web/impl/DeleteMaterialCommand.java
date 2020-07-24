package com.stormnet.andrewPiniuta.abz.web.impl;

import com.stormnet.andrewPiniuta.abz.data.Material;
import com.stormnet.andrewPiniuta.abz.data.MaterialName;
import com.stormnet.andrewPiniuta.abz.service.MaterialService;
import com.stormnet.andrewPiniuta.abz.service.ServiceFactory;
import com.stormnet.andrewPiniuta.abz.web.Command;
import com.stormnet.andrewPiniuta.abz.web.Request;
import com.stormnet.andrewPiniuta.abz.web.Response;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class DeleteMaterialCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException {
        //достаем из объекта request данные o ID объекта для удаления
        Integer ID = ((Number) request.getParameter("ID")).intValue();
//создание сервис объектов и вызов их методов
        ServiceFactory sf = ServiceFactory.getServiceFactory();
        MaterialService ms = sf.getMaterialService();
        ms.deleteMaterial(ID);
    }
}
