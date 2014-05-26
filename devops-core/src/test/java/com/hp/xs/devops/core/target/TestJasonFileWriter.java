package com.hp.xs.devops.core.target;

import com.hp.xs.devops.core.common.Setting;
import com.hp.xs.devops.core.etl.Entity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * TestJasonFileWriter.java
 * @author StevenGe
 */
public class TestJasonFileWriter {
    private HashMap<String, String> testEntityParams = new HashMap<String, String>();
    private HashMap<String, String> testEntityParams_2 = new HashMap<String, String>();
    private Entity testEntity = new Entity();
    private Entity testEntity_2 = new Entity();
    private JsonFileWriter testJsonFileWriter = new JsonFileWriter();

    @Before
    public void setup() {
        String testJasonFilePath = "C:\\Work\\test\\json\\testEntity.json";

        testEntityParams.put(Setting.ENTITY_NAME_FIELD, "ActualCost");
        testEntityParams.put(Setting.ETL_TYPE_FIELD, "NonConsolidation_InitialLoad");
        testEntityParams.put(Setting.CONTAINED_CP_NAMES, "[ AM ]");
        testEntityParams.put(Setting.COMPARE_RESULT, "true");

        testEntityParams_2.put(Setting.ENTITY_NAME_FIELD, "PlannedCost");
        testEntityParams_2.put(Setting.ETL_TYPE_FIELD, "NonConsolidation_Inset");
        testEntityParams_2.put(Setting.CONTAINED_CP_NAMES, "[ AM ]");
        testEntityParams_2.put(Setting.COMPARE_RESULT, "false");

        testEntity.initializeEntityByMap(testEntityParams);
        testEntity_2.initializeEntityByMap(testEntityParams_2);
        testJsonFileWriter.setWriteTarget(testJasonFilePath);
    }

    @After
    public void teardown() {
        // empty implemented
    }

    @Test
    public void testWriteEntityToJason() {
        testJsonFileWriter.writeEntityToJsonFile(testEntity);
        testJsonFileWriter.writeEntityToJsonFile(testEntity_2);
    }
}
