package com.hp.xs.devops.core.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EntityResult.java
 * This class encapsulate the etl result for every entity
 */
public class EntityResult {
    private String entityName;
    private String etlResult;
    private String containedCPNames;
    private String etlType;

    public boolean isEtlType(String etlTypeRegx) {
        String typePattern;
        if(etlTypeRegx.equalsIgnoreCase("Init")) {
            typePattern = "InitialLoad";
        } else {
            typePattern = "(Insert|SCD1|SCD2|Delete|QualifierChange|InitSync|Upd1Sync|Upd2Sync)";
        }
        Pattern pattern = Pattern.compile(typePattern);
        Matcher matcher = pattern.matcher(this.etlType);
        return matcher.find();
    }

    public String toString() {
        return entityName + " " + etlType +" " + etlResult + " to compare in " + containedCPNames;
    }
    // getters & setters
    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEtlResult() {
        return etlResult;
    }

    public void setEtlResult(String etlResult) {
        this.etlResult = etlResult;
    }

    public String getContainedCPNames() {
        return containedCPNames;
    }

    public void setContainedCPNames(String containedCPNames) {
        this.containedCPNames = containedCPNames;
    }

    public String getEtlType() {
        return etlType;
    }

    public void setEtlType(String etlType) {
        this.etlType = etlType;
    }
}
