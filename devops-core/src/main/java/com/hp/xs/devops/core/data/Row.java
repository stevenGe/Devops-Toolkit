package com.hp.xs.devops.core.data;

import com.hp.xs.devops.core.common.Setting;

import java.util.HashMap;


/**
 * This file defines the row structure.
 * @author SteveGe
 * @version 1.0-snapshot
 */
public class Row {
    private HashMap<String, String> rowData;			//column Index, value

    public Row(){
        rowData = new HashMap<String, String>();
    }

    public int getFieldSize(){
        return rowData.size();
    }

    public void setFieldValue(int colIndex, String fValue){
        if(fValue != null && fValue.length() > Setting.AUTODB_FIELD_LENGTH) {
            fValue = fValue.substring(0, Setting.AUTODB_FIELD_LENGTH);
        }
        rowData.put(String.valueOf(colIndex), fValue);
    }

    public String getFieldValue(int colIndex){
        return rowData.get(String.valueOf(colIndex));
    }
}
