package com.hp.xs.devops.core.common;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import com.hp.xs.devops.core.data.EntityResult;


/**
 * TestETLUtil.java
 * @author StevenGe
 */


public class TestETLUtil {
    private String source;

    @Before
    public void setup() {
        this.source = "Line 352: 2014-04-02 10:13:35,563 [pool-2-thread-33] (CheckContentDataAction.java:122) INFO " +
                " - [ AM ]ActualCost_NonConsolidation_InitialLoad true to compare!";
    }

    @After
    public void teardown() {
        // empty implemented
    }

    @Test
    public void testAnalyticLogResult() {
        HashMap<String, String> expectedRes = new HashMap<String, String>();
        expectedRes.put(Setting.ENTITY_NAME_FIELD, "ActualCost");
        expectedRes.put(Setting.ETL_TYPE_FIELD, "NonConsolidation_InitialLoad");
        expectedRes.put(Setting.CONTAINED_CP_NAMES, "[ AM ]");
        expectedRes.put(Setting.COMPARE_RESULT, "true");
        EntityResult result = ETLUtil.analyticLogResult(source);
        assertEquals(expectedRes.get(Setting.ENTITY_NAME_FIELD), result.getEntityName());
        assertEquals(expectedRes.get(Setting.ETL_TYPE_FIELD), result.getEtlType());
        assertEquals(expectedRes.get(Setting.CONTAINED_CP_NAMES), result.getContainedCPNames());
        assertEquals(expectedRes.get(Setting.COMPARE_RESULT), result.getEtlResult());
    }
}
