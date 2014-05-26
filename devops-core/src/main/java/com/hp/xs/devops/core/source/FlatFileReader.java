package com.hp.xs.devops.core.source;

import java.io.BufferedReader;
import java.util.ArrayList;

import com.hp.xs.devops.core.common.Setting;

/**
 * This class implements SourceReader method.
 * @author StevenGe
 * @version 1.0-snapshot
 */
public class FlatFileReader implements SourceReader {
    private BufferedReader bReader;
    private String lineSeparator = Setting.ROW_SEPARATOR;
    private String fieldSeparator = Setting.COLUMN_SEPARATOR;

    @Override
    public void setReadSource(String fileNameORtblName) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ArrayList<String> readTitle() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ArrayList<String> readLine() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void close() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setLineSeparator(String lineSeparator) {
        this.lineSeparator = lineSeparator;
    }

    public void setFieldSeparator(String fieldSeparator) {
        this.fieldSeparator = fieldSeparator;

    }
}
