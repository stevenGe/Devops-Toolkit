package com.hp.xs.devops.core.source;

import au.com.bytecode.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 * This class implements SourceReader interface.
 * @author StevenGe
 * @version 1.0-snapshot
 */
public class CSVFileReader implements SourceReader {

    private Reader reader;
    private String fileName;

    @Override
    public void setReadSource(String fileNameORtblName) {
        this.fileName = fileNameORtblName;
        File file = new File(fileName);
        if(!file.exists()) {
            // print out the error log
        }
        this.reader = null;
    }

    @Override
    public ArrayList<String> readTitle() {
        ArrayList<String> list = new ArrayList<String>();
        return list = readLine();
    }

    @Override
    public ArrayList<String> readLine() {
        ArrayList<String> list = new ArrayList<String>();
        try {
            if(reader != null) {
                reader.close();
            }
            reader = new FileReader(new File(fileName));
            CSVReader csvReader = new CSVReader(reader);
            String[] nextLine;
            if ((nextLine = csvReader.readNext()) != null) {
                for(int i = 0; i < nextLine.length; i++) {
                    list.add(nextLine[i]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list.size() > 0 ? list : null;
    }

    @Override
    public void close() {
        // Nothing need to be closed.
    }

    public void setSheetName(String sheetName) {
        // Nothing to do here
    }
}
