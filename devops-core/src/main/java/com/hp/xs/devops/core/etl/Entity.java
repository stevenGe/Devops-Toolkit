package com.hp.xs.devops.core.etl;

import com.hp.xs.devops.core.common.Setting;

import java.util.HashMap;

/**
 * Entity.java
 * This class represents the object for entity
 * @author StevenGe
 */
public class Entity {
    private String entityName;
    private String compareResult;
    private String etlType;
    private String containedCPs;

    public Entity() {
        // empty implemented
    }

    public Entity(String entityName, String compareResult, String etlType, String containedCPs) {
        this.entityName = entityName;
        this.compareResult = compareResult;
        this.etlType = etlType;
        this.containedCPs = containedCPs;
    }

    public void initializeEntityByMap(HashMap<String, String> parameters) {
        if (parameters == null || parameters.size() == 0) {
            throw new RuntimeException("Error, No parameters found in the map");
        } else {
            this.entityName = parameters.get(Setting.ENTITY_NAME_FIELD);
            this.compareResult = parameters.get(Setting.COMPARE_RESULT);
            this.etlType = parameters.get(Setting.ETL_TYPE_FIELD);
            this.containedCPs = parameters.get(Setting.CONTAINED_CP_NAMES);
        }
    }

    @Override
    public String toString() {
        return "Entity [" + Setting.ENTITY_NAME_FIELD + "=" + this.entityName +
                    ", " + Setting.CONTAINED_CP_NAMES + "=" + this.containedCPs +
                    ", " + Setting.ETL_TYPE_FIELD + "=" + this.etlType +
                    ", " + Setting.COMPARE_RESULT + "=" + this.compareResult + "]";
    }

    // getters & setters
    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getCompareResult() {
        return compareResult;
    }

    public void setCompareResult(String compareResult) {
        this.compareResult = compareResult;
    }

    public String getEtlType() {
        return etlType;
    }

    public void setEtlType(String etlType) {
        this.etlType = etlType;
    }

    public String getContainedCPs() {
        return containedCPs;
    }

    public void setContainedCPs(String containedCPs) {
        this.containedCPs = containedCPs;
    }
}
