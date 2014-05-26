package com.hp.xs.devops.core.target;

/**
 * TargetWriter.java
 */
public interface TargetWriter {
    public void setWriteTarget(String fileNameORtblName);
    public void writeTitle(String content);
    public void writeLine(String content);
    public void close();
}
