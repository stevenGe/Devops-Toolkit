package com.hp.xs.devops.core.common;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: gex
 * Date: 2/28/14
 * Time: 11:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class Setting {
    /**************************************************************
     * Setting For Content Comparing
     *************************************************************/
    public static final int AUTODB_FIELD_LENGTH = 4000;
    public static final int RESULTSET_PAGE_SIZE = 200;
    public static final String ROW_SEPARATOR = "!$NL$!";
    public static final String COLUMN_SEPARATOR = "!$NC$!";
    public static final String DATETIMEFORMAT = "yyyy-MM-dd HH:mm:ss";


    // Setting For fist log HashMap key-values
    public static final String ENTITY_NAME_FIELD = "ENTITY_NAME";
    public static final String COMPARE_RESULT = "COMPARE_RESULT";
    public static final String CONTAINED_CP_NAMES = "CONTAINED_CP_NAMES";
    public static final String ETL_TYPE_FIELD = "ETL_TYPE_FIELD";

    // Settings for contained Content pack names
    @SuppressWarnings("serial")
    public static ArrayList<String> ContentPacks = new ArrayList<String>(){{
        add("ALM"); add("ALT"); add("AM"); add("BSM");
        add("DP"); add("NA"); add("OO"); add("PPM");
        add("SA"); add("SM"); add("SE"); add("UCMDB");
        add("NNM"); add("IC");add("AWS"); add("CSA"); add("VCM");
        add("AWSCW"); add("HPV");
    }};
}
