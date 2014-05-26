package com.hp.xs.devops.core.source;

import com.hp.xs.devops.core.common.FileUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class implements SourceReader interface.
 * @author StevenGe
 * @version 1.0-snapshot
 */
public class ExcelFileReader implements SourceReader {


    private String sheetName;
    private FileInputStream fis;
    private int currRow;
    private String fileName;

    public ExcelFileReader() {
        sheetName = null;
    }

    @Override
    public void setReadSource(String fileNameORtblName) {
        this.fileName = fileNameORtblName;
        currRow = 0;
        File file = new File(fileName);
        if(!file.exists()) {
            // print out the error log
        }
        fis = null;
    }

    @Override
    public ArrayList<String> readTitle() {
        ArrayList<String> list = new ArrayList<String>();
        currRow = 0;
        try {
            if (fis != null)
                fis.close();
            fis = new FileInputStream(new File(fileName));
            POIFSFileSystem fs = new POIFSFileSystem(fis);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = null;
            if (sheetName == null) {
                sheet = wb.getSheetAt(0);
                sheetName = sheet.getSheetName();
            } else {
                sheet = wb.getSheet(sheetName);
            }

            HSSFRow row = sheet.getRow(currRow); // read the first line
            currRow++;
            if (row != null) {
                int len = row.getLastCellNum();
                for (int i = 0; i < len; i++) {
                    list.add(FileUtil.readCellValue(row.getCell(i)));
                }
            }

            fis.close();
            fis = null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    /*
    ** This method read one line of data from excel file once a time.
     */
    public ArrayList<String> readLine() {
        ArrayList<String> list = new ArrayList<String>();

        try {
            if (fis != null)
                fis.close();
            fis = new FileInputStream(new File(fileName));
            POIFSFileSystem fs = new POIFSFileSystem(fis);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheet(sheetName);
            int maxRow = sheet.getLastRowNum();
            if (currRow < maxRow) {
                HSSFRow row = sheet.getRow(currRow);
                currRow++;
                if (row != null) {
                    int len = row.getLastCellNum();
                    for (int i = 0; i < len; i++) {
                        list.add(FileUtil.readCellValue(row.getCell(i)));
                    }
                }
            }
            fis.close();
            fis = null;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return list.size() == 0 ? null : list;
    }

    @Override
    public void close() {
        // Nothing need to be closed.
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
}
