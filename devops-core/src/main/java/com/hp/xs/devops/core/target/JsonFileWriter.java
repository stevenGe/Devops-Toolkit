package com.hp.xs.devops.core.target;

/**
 * JsonFileWriter.java
 * @author StevenGe
 */

import java.io.File;
import java.io.IOException;

import com.hp.xs.devops.core.etl.Entity;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonFileWriter implements TargetWriter{
    private File jasonFile;

    @Override
    public void setWriteTarget(String fileNameORtblName){
        this.jasonFile = new File(fileNameORtblName);
    }

    @Override
    public void writeTitle(String content) {
        // empty implemented
    }

    @Override
    public void writeLine(String content) {
        // empty implemented
    }

    @Override
    public void close() {
        // empty implemented
    }

    public void writeEntityToJsonFile(Entity entity) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(jasonFile,entity);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
