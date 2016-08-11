/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.reports;

import com.emi.loan.Calc;
import java.util.Map;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author Arun
 */
public class SummaryPieReport extends ReportGenerator {

    private boolean isReportGenerated = false;

    @Override
    public void showReport(String _name,int width, int height) {
         super.showReport("Summary Report", width, height);
    }

    @Override
    public ReportGenerator generateReport() {
        if (!isReportGenerated) {
            DefaultPieDataset dataset = new DefaultPieDataset();
            dataset.setValue("Interest", Calc.getTotalInt());
            dataset.setValue("Principal", Calc.getTotalPrn());

            chart = ChartFactory.createPieChart(
                    "Loan Summary", // chart title
                    dataset, // data
                    true, // include legend
                    true,
                    false);
            isReportGenerated = true;
        }
        return this;
    }

}
