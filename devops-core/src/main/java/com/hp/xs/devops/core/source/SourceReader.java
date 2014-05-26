package com.hp.xs.devops.core.source;

import java.util.ArrayList;

/**
 * This interface is for source reader.
 * @author StevenGe
 * @version 1.0-snapshot
 */
public interface SourceReader {
    public void setReadSource(String fileNameORtblName);
    public ArrayList<String> readTitle();
    public ArrayList<String> readLine();
    public void close();
}
