package com.stormnet.andrewPiniuta.abz.db;

public class XmlException extends RuntimeException {
    public XmlException(Throwable cause) {
        super("Some error occured during access to XML DB", cause);
    }
}
