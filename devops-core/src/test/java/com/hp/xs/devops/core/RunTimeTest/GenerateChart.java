package com.hp.xs.devops.core.RunTimeTest;

import java.awt.Font;
import java.awt.Color;

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

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * GenerateChart.java
 */
public class GenerateChart {
    private int chartWidth = 600;
    private int chartHeight = 400;

    private void generateChart(String titleName, String dataPrefix, String dataSuffix, boolean isConsolidation, String jpgName){
        JFreeChart chart = ChartFactory.createStackedBarChart3D("HPXS Automation Test Summary Reports", "Data Source", "Test Case",
                getDataSet(dataPrefix, dataSuffix, isConsolidation),
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

    private CategoryDataset getDataSet(String prefix, String suffix, boolean isConsolidation){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int passed = 20;
        int total = 30;
        dataset.addValue(passed, "1", "AM");
        dataset.addValue(total - passed, "2", "AM");
        return dataset;
    }

    public static void main(String[] args) {
        GenerateChart generateChart = new GenerateChart();
        generateChart.generateChart("NonConsolidation Tests - Init Load", "NonConsolidation", "Init", false, "C:\\Work\\test\\FIST_logs\\report1.jpg");
    }

}
