package com.example.application.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPaths {
    public String readPropertyPath(String parameter){
        Properties prop = new Properties();
        String filename = "GUIConfig.properties";
        InputStream is = null;
        try{
            is = new FileInputStream(filename);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        try{
            prop.load(is);
        } catch (IOException e){
            e.printStackTrace();
        }
        return prop.getProperty(parameter);
    }
}
