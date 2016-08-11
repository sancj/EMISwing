/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.reports;

import java.util.Map;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.Dataset;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author Arun
 */
public abstract class ReportGenerator {

    public  JFreeChart chart = null;
    
    public abstract ReportGenerator generateReport();

    public  void showReport(String _name, int width, int height){
       
        JFrame jFrame = new JFrame(_name);
        if (chart == null) {
            System.out.println("Please generate chart..");
        }
        jFrame.setContentPane(new ChartPanel(chart));
        jFrame.setSize(width, height);
        RefineryUtilities.centerFrameOnScreen(jFrame);
        jFrame.setVisible(true);
    }

}
