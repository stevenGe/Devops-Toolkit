package com.hp.devops.xs.patch.parser;

/**
 * @author StevenGe
 * @version 1.0.0-snapshot
 * Date: 5/25/14
 * Time: 5:19 PM
 * Create this file for test patch resolver
 */

import com.hp.devops.xs.patch.operation.FileAction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import nu.xom.Element;
import nu.xom.Elements;

import java.util.ArrayList;


public class TestPatchResolver {
    private String patchXML;
    private PatchResolver resolver;

    @Before
    public void setup() {
        patchXML = "/Users/gexinlu/Downloads/patch.xml";
        resolver = new PatchResolver(patchXML);
        resolver.initialize();
    }

    @After
    public void teardown() {
        // empty implemented
    }


    @Test
    public void testGetRootElement() {
        Element rootElement = resolver.getPatchDoc().getRootElement();
        String rootName = rootElement.getLocalName();
        assertNotNull(rootElement);
        assertEquals("the two values are equals", "Patch", rootName);
    }

    @Test
    public void testgetPatchActions() {
        Element rootElement = resolver.getPatchDoc().getRootElement();
        Elements actionRootElement = rootElement.getChildElements();
        assertEquals("The Elements'size is 1", 1, actionRootElement.size());
    }

    @Test
    public void testResolveActions() {
        ArrayList<FileAction> testActionCollection = resolver.resolveActions();
        assertNotNull(testActionCollection);
    }


//    @Test
//    public void testgetActions() {
//        try {
//            Elements actions = resolver.getActions();
//            assertNotNull(actions);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
