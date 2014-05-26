package com.hp.xs.devops.core.common;

import org.apache.poi.hssf.usermodel.HSSFCell;

/**
 * File utilities.
 * @author StevenGe
 * @version 1.0-snapshot
 */
public class FileUtil {
    /**
     * As per cell type in the excel file, get the value
     * @param cell
     * @return String
     */
    public static String readCellValue(HSSFCell cell){
        String cellValue = null;
        if(cell != null){
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC:
                case HSSFCell.CELL_TYPE_FORMULA:
                    cellValue = String.valueOf( cell.getNumericCellValue() );
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    cellValue = cell.getStringCellValue().trim();
                    break;
                default:
                    cellValue = "";
            }
        }
        return cellValue;
    }
}
