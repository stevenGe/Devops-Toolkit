package com.hp.xs.devops.core.source;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class implements SourceReader method.
 * @author StevenGe
 * @version 1.0-snapshot
 */



public class FistLogFileReader implements SourceReader {
    private BufferedReader reader;

    @Override
    public void setReadSource(String fileNameORtblName) {
        try {
            File fileSource = new File(fileNameORtblName);
            this.reader = new BufferedReader(
                        new FileReader(fileSource));
        } catch (FileNotFoundException exception) {
            System.out.println("Cannot find the related file, please check!");
            throw new RuntimeException(exception);
        }
    }

    @Override
    public ArrayList<String> readTitle() {
        // Nothing implemented here
        return null;
    }

    @Override
    public ArrayList<String> readLine() {
        Pattern pattern = Pattern.compile("CheckContentDataAction\\.java");
        Matcher matcher;
        ArrayList<String> results = new ArrayList<String>();
        String line;
        try {
            while ( (line = reader.readLine()) != null) {
                matcher = pattern.matcher(line);
                if (matcher.find())
                    results.add(line);
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        return results.size() >0 ? results : null;
    }

    @Override
    public void close() {
        try{
            if (reader != null) {
                reader.close();
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

}