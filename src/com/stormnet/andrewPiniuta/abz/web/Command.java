package com.stormnet.andrewPiniuta.abz.web;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface Command {
    void execute(Request request, Response response) throws IOException, TransformerException, SAXException, ParserConfigurationException;
}
