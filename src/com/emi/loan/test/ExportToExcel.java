/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emi.loan.test;

/**
 *
 * @author Arun
 */
import java.io.File;
import java.io.FileNotFoundException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExportToExcel {

    public static void main(String[] args) {
        try {

//Populate DefaultTableModel data
            DefaultTableModel dtm = new DefaultTableModel();
            Vector<String> cols = new Vector<String>();
            dtm.addColumn("Col 1");
            dtm.addColumn("Col 2");
            dtm.addColumn("Col 3");

            Vector<String> dtmrow = null;
            for (int i = 1; i <= 10; i++) {
                dtmrow = new Vector<String>();
                for (int j = 1; j <= 3; j++) {
                    dtmrow.add("Cell " + j + "." + i);
                }
                dtm.addRow(dtmrow);
            }

//Exporting to Excel           
            Workbook wb = new HSSFWorkbook();
            CreationHelper createhelper = wb.getCreationHelper();
            Sheet sheet = wb.createSheet("new sheet");
            Row row = null;
            Cell cell = null;
            for (int i = 0; i < dtm.getRowCount(); i++) {
                row = sheet.createRow(i);
                for (int j = 0; j < dtm.getColumnCount(); j++) {

                    cell = row.createCell(j);
                    cell.setCellValue((String) dtm.getValueAt(i, j));
                }
            }

            FileOutputStream out = new FileOutputStream(new File("C:\\26276\\workbook.xls"));
            wb.write(out);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExportToExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExportToExcel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
