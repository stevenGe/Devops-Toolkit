package com.hp.xs.devops.core.runtimeTest;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

import com.hp.xs.devops.core.data.Row;
import com.hp.xs.devops.core.source.*;
import com.hp.xs.devops.core.common.*;

/**
 * This class is for all function to run the test.
 * current function is importDataToDB
 * @author StevenGe
 * @version 1.0-snapshot
 */
public class RunTimeTest {

    public static String GlobalPstSentence = null;



    public static void  main(String[] args) {
        String sourceFilePath = "C:\\Users\\GEX\\Desktop\\jackie";
        importDataToDB( findFileSource(sourceFilePath));
    }

    public static String findFileSource(String path) {
        String result = null;
        File files = new File(path);
        if(files.isDirectory()) {
            String[] subDirectoryFiles = files.list();
            for (String oneFile : subDirectoryFiles) {
                result = path + File.separator +oneFile;
            }
        } else if(files.isFile()) {
            result = path;
        } else {
            System.out.println("The given file path is not a directory or a file, pleae check!");
        }
        return result;
    }

    public static void importDataToDB(String filePath) {
        SourceReader srf = SourceReaderFactory.getReaderInstance("excelfile");
        srf.setReadSource(filePath);
        String tableName = filePath.toUpperCase().substring(
                filePath.lastIndexOf("\\"),
                        filePath.lastIndexOf(".")
        );
        ArrayList<String> title = srf.readTitle();
        createTable(tableName, title);
        ArrayList<Row> lines = new ArrayList<Row>();
        ArrayList<String> oneLine = null;
        while ( (oneLine = srf.readLine() ) != null) {
            lines.add(line2Row(oneLine));
        }
        writeToDB(tableName, lines);
    }

    public static Row line2Row(ArrayList<String> inData) {
        Row rowOutData = new Row();
        for ( int index = 0; index < inData.size(); index++) {
            rowOutData.setFieldValue(index, inData.get(index));
        }
        return rowOutData;
    }

    /**
     * create table in db
     * @param tblName
     * @param title
     */
    public static  void createTable (String tblName, ArrayList<String> title) {
        Connection dbConn = null;
        String schemaName = "dbo";
        String dbName = "excelDB";
        String pstSentence;

        int len = title.size();
        String createTblSql = "CREATE TABLE "  + dbName + "." + schemaName + "." + tblName + " ( ";
        pstSentence = "INSERT INTO " + dbName + "." + schemaName + "." + tblName + " VALUES (";
        for(int i = 0; i < len; i++){
            if (!createTblSql.endsWith("( "))
                createTblSql += ", ";

            createTblSql += title.get(i) + " NVARCHAR(" + Setting.AUTODB_FIELD_LENGTH + ")";
            if (pstSentence.endsWith("?"))
                pstSentence += ", ";
            pstSentence += "?";
        }
        createTblSql += ")";
        pstSentence += ")";

        System.out.println(createTblSql);
        System.out.println(pstSentence);
        GlobalPstSentence = pstSentence;
        Statement st = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://" + "16.186.78.185" + ":1433;" +
                    "databaseName=" + dbName + ";user=sa;password=openview;";
            dbConn = DriverManager.getConnection(connectionUrl);

            String mTbl = dbName + "." + schemaName + "." + tblName;
            st = dbConn.createStatement();
            st.execute("if object_id('" + mTbl + "') is not null drop table " + mTbl);
            st.execute(createTblSql);
            st.close();
        } catch (SQLException se){
            System.out.println(se.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Save data into table
     * @param tblName
     * @param inData
     */
    public static void  writeToDB (String tblName, ArrayList<Row> inData){
        Connection dbConn = null;
        String schemaName = "dbo";
        String dbName = "excelDB";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://" + "16.186.78.185" + ":1433;" +
                    "databaseName=" + dbName + ";user=sa;password=openview;";
            dbConn = DriverManager.getConnection(connectionUrl);

            dbConn.setAutoCommit(false);
            PreparedStatement pst = dbConn.prepareStatement(GlobalPstSentence);
            for (Row oneRow : inData) {
                int len = oneRow.getFieldSize();
                for(int i = 0; i < len ; i ++){
                    String tmp = oneRow.getFieldValue(i);
                    if (tmp == null || tmp.equals("")) {
                        pst.setNull(i + 1, java.sql.Types.VARCHAR);
                    } else {
                        pst.setString(i + 1, tmp);
                    }
                }
                pst.addBatch();
            }
            pst.executeBatch();
            dbConn.commit();
            dbConn.setAutoCommit(true);
            pst.close();
        } catch (SQLException se){
            System.out.println(se.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
