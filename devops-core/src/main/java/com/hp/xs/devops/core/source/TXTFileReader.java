package com.hp.xs.devops.core.source;

import java.util.ArrayList;

/**
 * This class implements SourceReader method.
 * @author StevenGe
 * @version 1.0-snapshot
 */


public class TXTFileReader implements SourceReader {
    @Override
    public void setReadSource(String fileNameORtblName) {
        // Nothing implemented here
    }

    @Override
    public ArrayList<String> readTitle() {
        // Nothing implemented here
        return null;
    }

    @Override
    public ArrayList<String> readLine() {
        // Nothing implemented here
        return null;
    }

    @Override
    public void close() {
        // Nothing implemented here
    }
}
