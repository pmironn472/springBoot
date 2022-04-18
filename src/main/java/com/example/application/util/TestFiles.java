package com.example.application.util;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestFiles {

    public List<String> lookForTestFiles(File folder){
        File[] listOfFiles = folder.listFiles();
        List<String> name = new ArrayList<>();

        for (File file : listOfFiles) {
            if (file.isFile()) {
               name.add(file.getName());
            }
        }
        return name;
    }

    public List<String> lookForEngineerProfile(File folder){
        File[] listOfFiles = folder.listFiles();
        List<String> name = new ArrayList<>();

        for (File file : listOfFiles) {
            if (file.isDirectory()) {
                name.add(file.getName());
            }
        }
        return name;
    }

}
