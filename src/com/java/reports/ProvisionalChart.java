/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.reports;

import com.emi.loan.EMI;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author Arun
 */
public class ProvisionalChart extends ReportGenerator {

    private boolean isReportGenerated = false;

    private final DefaultCategoryDataset _datasetProvisional = new DefaultCategoryDataset();

    private final List<EMI> ls_EMI;

    public ProvisionalChart(List<EMI> ls_EMI) {
        this.ls_EMI = ls_EMI;
    }

    @Override
    public ReportGenerator generateReport() {
        if (!isReportGenerated) {
            if (processProvisionalReport()) {
                chart = ChartFactory.createBarChart3D("Provisional Chart",
                        "EMI Installments",
                        "INR", _datasetProvisional,
                        PlotOrientation.VERTICAL,
                        true, true, false);
                isReportGenerated = true;
            } else {
                System.out.println("Report Generation Failed - EMI list is empty..");
            }
        }
        return this;
    }

    private boolean processProvisionalReport() {
        double int_part = 0, prn_part = 0;
        int x = 1;

        if (ls_EMI.isEmpty()) {
            return false;
        }
        for (EMI e : ls_EMI) {
            if (e.getE_count() % 12 == 0) {
                System.out.println("YEAR: " + x + " : Interest: " + int_part + " Principal: " + prn_part);

                _datasetProvisional.addValue(int_part, "Interest", "YEAR - " + x);
                _datasetProvisional.addValue(prn_part, "Principal", "YEAR - " + x);

                ++x;
                int_part = 0;
                prn_part = 0;
            } else {
                int_part += e.getIntPart();
                prn_part += e.getPrnPart();
            }
        }
        if (int_part != 0 && prn_part != 0) {
            _datasetProvisional.addValue(int_part, "Interest", "YEAR - " + x);
            _datasetProvisional.addValue(prn_part, "Principal", "YEAR - " + x);
            System.out.println("YEAR: " + x + " : Interest: " + int_part + " Principal: " + prn_part);
        }

        return true;
    }

    @Override
    public void showReport(String _name, int width, int height) {
        super.showReport("Provisional Break-up Report", width, height);
    }

}
