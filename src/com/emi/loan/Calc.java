/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emi.loan;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Arun
 */
public class Calc {

    private static EMI emi;
    private static List<EMI> ls = new LinkedList<>();
    private static double totalInt;
    private static double totalPrn;

    public static List<EMI> calcEMI(Loan loan, double prepaid) {
        ls.clear();
        double intPart = 0, prnPart = 0;
        double intPartOpt = 0, prnPartOpt = 0;

        int count = 1;

        for (int i = loan.getPeriod(); i > 0; i--) {
            emi = new EMI();

            double principal = loan.getPrincipal();
            int period = loan.getPeriod();
            double roi = loan.getRoi();
            roi = (roi / 1200);
            double roi_period = Math.pow((1 + roi), period);

            emi.setEmi((principal * roi * roi_period) / (roi_period - 1));
            emi.setIntPart(principal * roi);

            intPart += emi.getIntPart();

            emi.setPrnPart(emi.getEmi() - emi.getIntPart());
            prnPart += emi.getPrnPart();

            emi.setE_period((period - 1));
            emi.setE_count(count);

            emi.setE_principal(principal - emi.getPrnPart());

            double princOpt = loan.getPrincipalOpt();

            emi.setEmiOpt(emi.getEmi());

            if (loan.getPrincipalOpt() > 1) {
                emi.setIntPartOpt(princOpt * roi);

                intPartOpt += emi.getIntPartOpt();

                emi.setPrnPartOpt(emi.getEmiOpt() - emi.getIntPartOpt());
                prnPartOpt += emi.getPrnPartOpt();
            } else {
                emi.setIntPartOpt(0);

                intPartOpt += 0;

                emi.setPrnPartOpt(0);
                prnPartOpt += 0;
            }

            emi.setE_principalOpt(princOpt - emi.getPrnPartOpt() - prepaid);

            loan.setPrincipalOpt(emi.getE_principalOpt());

            ls.add(emi);

            count = count + 1;
            loan.setPeriod(emi.getE_period());
            loan.setPrincipal(emi.getE_principal());

            emi = null;

        }

        setTotalInt(intPart);
        setTotalPrn(prnPart);
        return ls;
    }

    /**
     * @return the totalInt
     */
    public static double getTotalInt() {
        return totalInt;
    }

    /**
     * @param totalInt the totalInt to set
     */
    public static void setTotalInt(double totalInt) {
        Calc.totalInt = Math.round((totalInt) * 100.0) / 100.0;
    }

    /**
     * @return the totalPrn
     */
    public static double getTotalPrn() {
        return totalPrn;
    }

    /**
     * @param totalPrn the totalPrn to set
     */
    public static void setTotalPrn(double totalPrn) {
        Calc.totalPrn = Math.round((totalPrn) * 100.0) / 100.0;
    }

}
