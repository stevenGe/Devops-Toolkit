package com.hp.xs.devops.core.runner;

/**
 * TestGenerateETLReportFromLog.java
 * This class is to test GenerateETLReportFromLog class
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.hp.xs.devops.core.data.EntityResult;
import com.hp.xs.devops.core.common.Setting;

public class TestGenerateETLReportFromLog {
    private GenerateETLReportFromLog testObject;
    private ArrayList<String> expectFiles = new ArrayList<String>();

    @Before
    public void setup() {
        String logFilesDir = "C:\\Work\\test\\FIST_logs";
        testObject = new GenerateETLReportFromLog();
        expectFiles.add("C:\\Work\\test\\FIST_logs\\session1\\session.log");
        expectFiles.add("C:\\Work\\test\\FIST_logs\\session2\\session.log");
        testObject.findLogFiles(logFilesDir);
        testObject.analyseLogs();
    }

    @After
    public void teardown() {
        //empty implement
    }

    @Test
    public void testFindLogFiles() {
        assertTrue(testObject.result.containsAll(expectFiles));
        assertTrue(expectFiles.containsAll(testObject.result));
    }

    @Test
    public void testAnalyseLogs() {
        assertNotNull(testObject.analyseRes);
        assertEquals(596, testObject.analyseRes.size());
    }

    @Test
    public void testGeneratePreChart() {
        HashMap<String, ArrayList<EntityResult>> testResultEntity;
        testResultEntity = testObject.generatePreChart();
        System.out.println(testResultEntity);
        for(String oneCP : Setting.ContentPacks) {
            assertTrue(testResultEntity.containsKey(oneCP));
        }
    }

    @Test
    public void testGetDataSet() {

    }
}
