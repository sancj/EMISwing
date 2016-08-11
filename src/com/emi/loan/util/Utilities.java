/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emi.loan.util;

import com.emi.loan.test.TestEMI;
import de.javasoft.plaf.synthetica.SyntheticaLookAndFeel;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JFileChooser.SAVE_DIALOG;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Arun
 */
public class Utilities {

    public static void printTable(JTable jTable) {

        MessageFormat header = new MessageFormat("EMI Table - Monthly Break-up ");

        MessageFormat footer = new MessageFormat("--------------- ");


        /* determine the print mode */
        JTable.PrintMode mode = JTable.PrintMode.FIT_WIDTH;

        try {
            /* print the table */
            boolean complete = jTable.print(mode, header, footer,
                    true, null,
                    false, null);

            /* if printing completes */
            if (complete) {
                /* show a success message */
                JOptionPane.showMessageDialog(null,
                        "Printing Complete",
                        "Printing Result",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                /* show a message indicating that printing was cancelled */
                JOptionPane.showMessageDialog(null,
                        "Printing Cancelled",
                        "Printing Result",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException pe) {
            /* Printing failed, report to the user */
            JOptionPane.showMessageDialog(null,
                    "Printing Failed: " + pe.getMessage(),
                    "Printing Result",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void applyTheme(String themeName, javax.swing.JFrame mainFrame) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (themeName.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFrame.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        SwingUtilities.updateComponentTreeUI(mainFrame.getRootPane());
    }

    public static void applySyntheticaTheme(SyntheticaLookAndFeel syn, javax.swing.JFrame mainFrame) {
        UIManager.put("Synthetica.window.decoration", Boolean.FALSE);

        try {
            UIManager.setLookAndFeel(syn);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }

        SwingUtilities.updateComponentTreeUI(mainFrame.getRootPane());
    }

    public static void exportTOExcel(DefaultTableModel dtm, Map<String, String> ln_info) {
        FileOutputStream out = null;
        try {
            Workbook wb = new HSSFWorkbook();
//            CreationHelper createhelper = wb.getCreationHelper();
            Sheet sheet = wb.createSheet("EMI TABLE");
            Row row;
            Cell cell;
            File file = chooseFile();
            out = new FileOutputStream(file);

            HSSFFont headerFont = (HSSFFont) wb.createFont();
            headerFont.setFontHeightInPoints((short) 12);
            headerFont.setFontName("CENTURY GOTHIC");
            headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            headerFont.setColor(HSSFColor.WHITE.index);

            HSSFFont infoFont = (HSSFFont) wb.createFont();
            infoFont.setFontHeightInPoints((short) 14);
            infoFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

//            HSSFFont font = (HSSFFont) wb.createFont();
//            font.setFontHeightInPoints((short) 10);
//            font.setFontName("CENTURY GOTHIC");
//            font.setColor(HSSFColor.BLACK.index);
            CellStyle defaultStyle = wb.createCellStyle();
            defaultStyle.setFillForegroundColor(HSSFColor.AQUA.index);
            defaultStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            defaultStyle.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
            defaultStyle.setVerticalAlignment(
                    HSSFCellStyle.VERTICAL_JUSTIFY);
            defaultStyle.setFont(headerFont);

            CellStyle borderStyle = wb.createCellStyle();
            borderStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
            borderStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
            borderStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
            borderStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
            borderStyle.setFont(infoFont);

            row = sheet.createRow(1);
            cell = row.createCell(0);
            cell.setCellStyle(defaultStyle);
            cell.setCellValue("Loan Amount(Rs.)");

            cell = row.createCell(1);
            cell.setCellStyle(borderStyle);
            cell.setCellValue(Double.parseDouble(ln_info.get("Loan Amount")));

            row = sheet.createRow(3);
            cell = row.createCell(0);
            cell.setCellStyle(defaultStyle);
            cell.setCellValue("Interest %");

            cell = row.createCell(1);
            cell.setCellStyle(borderStyle);
            cell.setCellValue(Double.parseDouble(ln_info.get("Interest")));

            row = sheet.createRow(5);
            cell = row.createCell(0);
            cell.setCellStyle(defaultStyle);
            cell.setCellValue("Period (months)");

            cell = row.createCell(1);
            cell.setCellStyle(borderStyle);
            cell.setCellValue(Integer.parseInt(ln_info.get("Period")));

            for (int i = 0; i <= dtm.getRowCount(); i++) {

                row = sheet.createRow(i + 8);
                for (int j = 0; j < dtm.getColumnCount(); j++) {
                    cell = row.createCell(j);

                    if (i == 0) { // writing the column headers 

                        cell.setCellStyle(defaultStyle);
                        cell.setCellValue(dtm.getColumnName(j));

                    } else if (j == 0 || j == 5) {
                        cell.setCellValue(Integer.parseInt(dtm.getValueAt(i - 1, j).toString()));
                    } else {
                        cell.setCellValue(Double.parseDouble(dtm.getValueAt(i - 1, j).toString()));
                    }

                }
            }

            row = sheet.createRow(dtm.getRowCount() + 12);
            cell = row.createCell(0);
            cell.setCellValue("-- END OF REPORT --");

            for (int j = 0; j < dtm.getColumnCount(); j++) {
                sheet.autoSizeColumn(j, true);
            }
            wb.write(out);
        } catch (FileNotFoundException ex) {
            System.out.println("File not Found");
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("IOException");
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static File chooseFile() {
        JFileChooser chooser = new JFileChooser() {
            @Override
            public void approveSelection() {
                File f = new File(getSelectedFile().getAbsolutePath().concat(".xls"));
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
        chooser.setDialogTitle("Save ");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(new ExcelFileFilter());
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = new File(chooser.getSelectedFile().getAbsolutePath().concat(".xls"));
                boolean isFile = false;
                if (!file.exists()) {
                    isFile = file.createNewFile();
                }
                if (isFile) {
                    return file;
                }

            } catch (IOException ex) {
                Logger.getLogger(TestEMI.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("No Selection ");
        }

        return null;
    }

    private static class ExcelFileFilter extends javax.swing.filechooser.FileFilter {

        @Override
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().toLowerCase().endsWith(".xls");
        }

        @Override
        public String getDescription() {
            return "Excel Files( *.xls)";
        }
    }

}
