package com.hp.devops.xs.patch.resources;

/**
 * Created with IntelliJ IDEA.
 * User: gex
 * Date: 5/28/14
 * Time: 4:17 PM
 * FileResource represent a file resource (jar, war, property files, etc...)
 */
public class FileResource {
    private String fileName;
    private String filePath;
    private String targetFilePath;

    public FileResource(String fileName, String filePath, String targetFilePath) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.targetFilePath = targetFilePath;
    }

    // getters
    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getTargetFilePath() {
        return targetFilePath;
    }
}
