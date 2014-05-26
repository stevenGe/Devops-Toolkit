package com.hp.xs.devops.core.source;



/**
 * This is a factory to use source reader.
 * @author StevenGe
 * @version 1.0-snapshot
 */
public class SourceReaderFactory {
    public static SourceReader getReaderInstance(String type, Object... parameters) {

    //    if (parameters == null || parameters.length < 1) return null;

        SourceReader sReader = null;
        if ("flatfile".equalsIgnoreCase(type)) {
            FlatFileReader fr = new FlatFileReader();
            if (parameters.length > 2) {
                fr.setLineSeparator(parameters[1].toString());
            }
            if (parameters.length > 3) {
                fr.setLineSeparator(parameters[2].toString());
            }
            sReader = (SourceReader)fr;
        } else if ("excelfile".equalsIgnoreCase(type)) {
            ExcelFileReader er = new ExcelFileReader();
            if (parameters.length > 2) {
                er.setSheetName(parameters[1].toString());
            }
            sReader = (SourceReader)er;
        } else if ("csvfile".equalsIgnoreCase(type)) {
            CSVFileReader cr = new CSVFileReader();
            if (parameters.length > 2) {
                cr.setSheetName(parameters[1].toString());
            }
        } else if ("database".equalsIgnoreCase(type)) {
            // nothing to do here
        } else if("logfile".equalsIgnoreCase(type)) {
            FistLogFileReader flreader = new FistLogFileReader();
            sReader = (SourceReader)flreader;
        } else {
            // empty implemented
        }

        return sReader;
    }
}
