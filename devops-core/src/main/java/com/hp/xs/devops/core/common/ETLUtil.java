package com.hp.xs.devops.core.common;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.hp.xs.devops.core.data.EntityResult;

/**
 * ETLUtil.java
 * @author StevenGe
 */
public class ETLUtil {
    /*
    ** This method is to analyse the fist log result
     * The method will return CP name, entity name, etl type name, compare result
     * example:
     *  "Line 446476: 2014-04-02 11:13:14,463 [pool-2-thread-60] (CheckContentDataAction.java:122) INFO  - [ ALT ]PlannedCost_NonConsolidation_InitialLoad true to compare!"
     *  "[ AM ]ActualCost_NonConsolidation_InitialLoad true to compare!"
     */
    public static EntityResult analyticLogResult(String input) {
        String[] result_tmp1 = input.split("\\(CheckContentDataAction\\.java:\\d++\\) INFO  - ");
        String right_value = result_tmp1[1];
        EntityResult result = new EntityResult();
        if (result_tmp1.length == 2) {
            // get compare result
            String compareRes = getEntityDetailInfoByRegx(right_value, "(true|false)");
            result.setEtlResult(compareRes);

            // get etl type
            String etlType = getEntityDetailInfoByRegx(right_value, "(NonCon|Con)solidation_(InitialLoad|Insert|SCD1|SCD2|Delete|QualifierChange|InitSync|Upd1Sync|Upd2Sync)");
            result.setEtlType(etlType);

            // get content pack name
            String contentPackName = getEntityDetailInfoByRegx(right_value, "\\[.*\\]");
            result.setContainedCPNames(contentPackName);

            // get entity name
            String entityName = getEntityDetailInfoByRegx(right_value, "\\].*_(NonCon|Con)solidation");
            entityName = entityName.substring(1, entityName.lastIndexOf("_"));
            result.setEntityName(entityName);
        }
        return result;
    }

    public static String getEntityDetailInfoByRegx(String input, String regx) {
        String result = null;
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()) {
            result = matcher.group();
        }
        return result;
    }
}
