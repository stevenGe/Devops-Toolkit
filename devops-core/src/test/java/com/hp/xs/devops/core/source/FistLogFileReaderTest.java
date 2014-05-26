package com.hp.xs.devops.core.source;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * FistLogFileReaderTest.java
 *
 */



public class FistLogFileReaderTest {
    private String logPath;
    private SourceReader sr;

    @Before
    public void setup() {
        String sessionDirPath = "C:\\Work\\test\\FIST_logs\\session1";
        File dir = new File(sessionDirPath);
        String[] subDirs = dir.list();
        this.logPath = sessionDirPath + File.separator + subDirs[0];
        this.sr = SourceReaderFactory.getReaderInstance("logfile");
        sr.setReadSource(logPath);
    }

    @After
    public void teardown() {
        sr.close();
    }

    @Test
    public void testReadDir() {
        assertEquals("C:\\Work\\test\\FIST_logs\\session1\\session.log", this.logPath);
    }

    @Test
    public void testCreateReader() {
        SourceReader sr = SourceReaderFactory.getReaderInstance("logfile");
        sr.setReadSource(logPath);
        ArrayList<String> results = sr.readLine();
        assertNotNull("source Reader is not null", sr);
        assertNull("results should not be null", results);
    }

    @Test
    public void testRegxExpression() {
        String input = "2014-04-02 10:13:35,563 [pool-2-thread-33] (CheckContentDataAction.java:122) INFO  - [ AM ]ActualCost_NonConsolidation_InitialLoad true to compare!";
        Pattern pattern = Pattern.compile("CheckContentDataAction\\.java");
        Matcher matcher = pattern.matcher(input);
        assertTrue("This result should be found in input string", matcher.find());
    }

    @Test
    public void testReadLine() {
        ArrayList<String> testResults = sr.readLine();
        assertNotNull(testResults);
    }
}
