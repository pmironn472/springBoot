package com.example.application.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadTestParameters {

    private ReadPaths readPaths = new ReadPaths();
    private BufferedReader readTestFile;

    public String loadTestParameters(String path, String lookingFor, String regex){
        File testFile = new File(readPaths.readPropertyPath("TEST_PROFILES_PATH") + path);
        String findText = "";
        String text = "";

        try {
            readTestFile = new BufferedReader(new FileReader(testFile));
            String line = readTestFile.readLine();
            while (line!=null){
                if (line.contains(lookingFor)){
                    text = new String(line.getBytes());
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(text);
                    if (matcher.find()){
                        findText = matcher.group(0);
                    }
                }
                line = readTestFile.readLine();
            }
            readTestFile.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return findText;
    }

}
