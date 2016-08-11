/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emi.loan.test;

import com.emi.loan.Calc;
import com.emi.loan.EMI;
import com.emi.loan.Loan;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Arun
 */
public class TestEMI {

    public static void main(String[] args) {
//        Loan loan = new Loan(100000, 18, 12);
//        List<EMI> l = Calc.calcEMI(loan, 2000);
//        Iterator it = l.iterator();
//        while (it.hasNext()) {
//            ((EMI) it.next()).printInfo();
//        }

        test();
    }

    private static void test() {
        JFileChooser chooser = new JFileChooser() {
            @Override
            public void approveSelection() {
                File f = getSelectedFile();
                if (f.exists() && getDialogType() == SAVE_DIALOG) {
                    int result = JOptionPane.showConfirmDialog(this, "The file exists, overwrite?", "Existing file", JOptionPane.YES_NO_CANCEL_OPTION);
                    switch (result) {
                        case JOptionPane.YES_OPTION:
                            super.approveSelection();
                            return;
                        case JOptionPane.NO_OPTION:
                            return;
                        case JOptionPane.CLOSED_OPTION:
                            return;
                        case JOptionPane.CANCEL_OPTION:
                            cancelSelection();
                            return;
                    }
                }
                super.approveSelection();
            }
        };
//        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Save ");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(new ExcelFileFilter());
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            FileWriter outFile = null;
            try {
                File file = new File(chooser.getSelectedFile().getAbsolutePath().concat(".xls"));
                boolean isFile = false;
                if (!file.exists()) {
                    isFile = file.createNewFile();
                }
                if (file.exists()) {
                    outFile = new FileWriter(file);
                    PrintWriter out = new PrintWriter(outFile, true);
                    out.close();
                } else {
                    System.out.println("File cannot be created..");
                }
            } catch (IOException ex) {
                Logger.getLogger(TestEMI.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    outFile.close();
                } catch (IOException ex) {
                    Logger.getLogger(TestEMI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            System.out.println("No Selection ");
        }

    }

    private static class ExcelFileFilter extends javax.swing.filechooser.FileFilter {

        @Override
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().toLowerCase().endsWith(".xls") ;
        }

        @Override
        public String getDescription() {
            return "Excel Files( *.xls)";
        }
    }

}
