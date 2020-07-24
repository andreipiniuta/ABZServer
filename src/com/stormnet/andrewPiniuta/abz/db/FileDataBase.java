package com.stormnet.andrewPiniuta.abz.db;

public enum FileDataBase {
    Material ("Material.xml");

    private String FileName;

    FileDataBase(String fileName) {
        FileName = fileName;
    }

    public String getFileName() {
        return FileName;
    }
}
