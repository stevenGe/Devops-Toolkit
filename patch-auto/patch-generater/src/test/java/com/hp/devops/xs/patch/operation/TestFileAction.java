package com.hp.devops.xs.patch.operation;

import com.hp.devops.xs.patch.parser.PatchResolver;
import com.hp.devops.xs.patch.resources.FileResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: gex
 * Date: 5/27/14
 * Time: 10:00 AM
 * TestFileAction.java
 */
public class TestFileAction {
    private String patchXML;
    private PatchResolver resolver;

    @Before
    public void setup() {
        patchXML = "C:\\Work\\github\\Devops-Toolkit\\patch-auto\\patch-generater\\src\\test\\resources\\Patch.xml";
        resolver = new PatchResolver(patchXML);
        resolver.initialize();
    }

    @After
    public void teardown() {
        // empty implement
    }

    @Test
    public void testCreateTargetFolder() {
        ArrayList<FileResource> fileActions = resolver.resolveActions();
        for(FileResource item : fileActions) {
            assertEquals("jmxsecurity.properties", item.getFileName());
            assertEquals("agora\\Conf\\jmxsecurity.properties", item.getFilePath());
            assertEquals("agora\\Conf\\jmxsecurity.properties", item.getTargetFilePath());
        }

    }
}
