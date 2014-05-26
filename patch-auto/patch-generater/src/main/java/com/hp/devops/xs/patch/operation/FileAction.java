package com.hp.devops.xs.patch.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import nu.xom.Element;
import java.io.File;

/**
 * @author StevenGe
 * @version 1.0.0-snapshot
 * Date: 5/25/14
 * Time: 5:19 PM
 * abstract class FileAction, abstract some operations here
 */

public class FileAction implements Action {
    Logger _logger = LoggerFactory.getLogger(FileAction.class);

    protected String fileName;
    protected String sourceDir;
    protected String targetDir;
    protected String requireFeatures;

    public FileAction(Element element) {

    }

    public FileAction(String fileName, String sourceDir, String targetDir, String requireFeatures) {
        this.fileName = fileName;
        this.sourceDir = sourceDir;
        this.targetDir = targetDir;
        this.requireFeatures = requireFeatures;
    }

    @Override
    public  void cleanFolder() {
        // empty implement
    }

    @Override
    public int copyFileToTargetFolder() {
        File sourceFile = getSourceFile();
        File targetDir = createTargetFolder();
        if( sourceFile == null || targetDir == null) {
            _logger.error("incorrect file resource or target resource, please check it!");
            return -1;
        }
        _logger.info(this.fileName + " successfully copied to " + this.targetDir + this.fileName);
        return 1;
    }

    private File getSourceFile() {
        File sourceFile = new File(this.sourceDir + File.separator + this.fileName);
        if(!sourceFile.exists()) {
            _logger.error("Source File: " + this.fileName + " does not exist, please check!");
            return null;
        }
        return sourceFile;
    }

    private File createTargetFolder() {
        File targetFileDir = new File(this.targetDir + File.separator);
        if( !(targetFileDir.exists() || targetFileDir.mkdirs()) ) {
            _logger.error("Failed to create target folder, please check!");
            return null;
        }
        return targetFileDir;
    }

    // getters & setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSourceDir() {
        return sourceDir;
    }

    public void setSourceDir(String sourceDir) {
        this.sourceDir = sourceDir;
    }

    public String getTargetDir() {
        return targetDir;
    }

    public void setTargetDir(String targetDir) {
        this.targetDir = targetDir;
    }

    public String getRequireFeatures() {
        return requireFeatures;
    }

    public void setRequireFeatures(String requireFeatures) {
        this.requireFeatures = requireFeatures;
    }

}
