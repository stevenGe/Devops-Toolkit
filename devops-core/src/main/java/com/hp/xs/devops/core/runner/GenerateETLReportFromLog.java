package com.hp.xs.devops.core.runner;


/**
 * GenerateETLReportFromLog.java
 * This class is to generate content nightly automation report from FIST log file(s).
 * This feature just supports NonConsolidation type.
 */

import java.awt.Font;
import java.awt.Color;

import java.io.FileOutputStream;
import java.io.IOException;

import com.hp.xs.devops.core.common.Setting;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.hp.xs.devops.core.source.SourceReader;
import com.hp.xs.devops.core.source.SourceReaderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hp.xs.devops.core.data.EntityResult;
import com.hp.xs.devops.core.common.ETLUtil;

public class GenerateETLReportFromLog {
    private int chartWidth = 600;
    private int chartHeight = 400;
    public ArrayList<String> result;
    public ArrayList<EntityResult> analyseRes;

    public GenerateETLReportFromLog() {
        result = new ArrayList<String>();
        analyseRes = new ArrayList<EntityResult>();
    }

    // recursively find log files
    public void findLogFiles(String location) {
        File items = new File(location);
        if (items.isDirectory()){
            String[] subItems = items.list();
            for(String oneItem : subItems) {
                findLogFiles(location + File.separator + oneItem);
            }
        } else if ( items.isFile() && items.getName().endsWith(".log")){
            result.add(items.getAbsolutePath());
        } else {
            // empty implement
        }
    }

    // read log files find matched lines
    public void analyseLogs() {
        SourceReader sourceReader = SourceReaderFactory.getReaderInstance("logfile");
        for(String oneLogFile : result) {
            sourceReader.setReadSource(oneLogFile);
            ArrayList<String> lines = sourceReader.readLine();
            for(String oneLine : lines) {
                analyseRes.add(ETLUtil.analyticLogResult(oneLine));
            }
        }
        sourceReader.close();
    }

    //  manipulate analyseRes to fit for generate DateSet
    public HashMap<String, ArrayList<EntityResult>> generatePreChart() {
        HashMap<String, ArrayList<EntityResult>> resultHashMap = new HashMap<String, ArrayList<EntityResult>>();
        for(String oneCP : Setting.ContentPacks) {
            ArrayList<EntityResult> oneCPList = new ArrayList<EntityResult>();
            resultHashMap.put(oneCP, oneCPList);
        }
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher;
        String cpName;
        for(EntityResult oneEntityResult : analyseRes) {
            cpName = oneEntityResult.getContainedCPNames();
            matcher = pattern.matcher(cpName);
            if(matcher.find()) {
                cpName = matcher.group();
            }
            resultHashMap.get(cpName).add(oneEntityResult);
        }
        return resultHashMap;
    }

    // generate automation report chart
    private void generateChart(String titleName, String dataPrefix, String dataSuffix, String jpgName){
        JFreeChart chart = ChartFactory.createStackedBarChart3D("HPXS Automation Test Summary Reports", "Data Source", "Test Case",
                getDataSet(dataPrefix, dataSuffix),
                PlotOrientation.VERTICAL, false, false, false);

        chart.setBackgroundPaint(new Color(249, 231, 236));	//pink

        chart.setTitle(new TextTitle(titleName,new Font("Garamond", Font.ITALIC, 20)));
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        plot.getRenderer().setSeriesPaint(0, new Color(90, 190, 110));	//green color
        plot.getRenderer().setSeriesPaint(1, new Color(225, 45, 45));	//red color

        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setLabelFont(new Font("Serif", Font.BOLD, 14));
        categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        categoryAxis.setTickLabelFont(new Font("Sansserif", Font.PLAIN, 12));

        NumberAxis numberAxis = (NumberAxis)plot.getRangeAxis();
        numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberAxis.setTickLabelFont(new Font("Sansserif", Font.PLAIN, 12));
        numberAxis.setLabelFont(new Font("Serif", Font.BOLD, 14));

        FileOutputStream fos = null;
        try{
            fos= new FileOutputStream(jpgName);
            ChartUtilities.writeChartAsJPEG(fos, 1, chart, chartWidth, chartHeight, null);
            fos.close();
        }catch(IOException ie){
            ie.printStackTrace();
        }

        //NonConsolidation Delta Testing Report
    }

    private CategoryDataset getDataSet(String prefix, String suffix){
        HashMap<String, ArrayList<EntityResult>> comparedRes = generatePreChart();
        ArrayList<EntityResult> oneEntityRes;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(String oneCP : Setting.ContentPacks) {
            oneEntityRes = comparedRes.get(oneCP);
            int passed = 0;
            int total = 0;
            ArrayList<EntityResult> specializedResults = new ArrayList<EntityResult>();
            for(EntityResult oneEntityResult : oneEntityRes) {
                if(oneEntityResult.isEtlType(suffix)) {
                    total += 1;
                    specializedResults.add(oneEntityResult);
                }
            }
            Iterator<EntityResult> it = specializedResults.iterator();
            while(it.hasNext()) {
                EntityResult entityResult = it.next();
                if(entityResult.getEtlResult().equalsIgnoreCase("true"))
                    passed += 1;
            }
            dataset.addValue(passed, "1", oneCP);
            dataset.addValue(total - passed, "2", oneCP);
        }
        return dataset;
    }

    public static void main(String[] args) {
        String logFilesDir = "C:\\Work\\test\\FIST_logs";
        GenerateETLReportFromLog generateChart = new GenerateETLReportFromLog();
        generateChart.findLogFiles(logFilesDir);
        generateChart.analyseLogs();
        generateChart.generatePreChart();
        generateChart.generateChart("NonConsolidation Tests - Init Load", "NonConsolidation", "Init", "C:\\Work\\test\\FIST_logs\\report1.jpg");
        generateChart.generateChart("NonConsolidation Tests - Delta Load", "NonConsolidation", "Delta", "C:\\Work\\test\\FIST_logs\\report2.jpg");
    }

}
