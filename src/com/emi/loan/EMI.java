/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emi.loan;

/**
 * E =( P * r * (1 + r)^ n) / ( (1 + r) ^ n - 1)
 * 
 * E
 * 
 * @author Arun
 */
public class EMI {
     
    private double _emi = 0;
    private double intPart = 0;
    private double prnPart = 0;
    
    
    private double e_principal;
    private int e_period;
    
    private int e_count;
    
    private double e_principalOpt;
    private double _emiOpt;
    private double intPartOpt = 0;
    private double prnPartOpt = 0;
    

    /**
     * @return the intPart
     */
    public double getIntPart() {
        return intPart;
    }

    /**
     * @param intPart the intPart to set
     */
    public void setIntPart(double intPart) {
        this.intPart = Math.round((intPart) * 100.0) / 100.0;
    }

    /**
     * @return the prnPart
     */
    public double getPrnPart() {
        return prnPart;
    }

    /**
     * @param prnPart the prnPart to set
     */
    public void setPrnPart(double prnPart) {
        this.prnPart = Math.round((prnPart) * 100.0) / 100.0;
    }

    /**
     * @return the _emi
     */
    public double getEmi() {
        return _emi;
    }

    /**
     * @param emi the _emi to set
     */
    public void setEmi(double emi) {
        this._emi = Math.round((emi) * 100.0) / 100.0;
    }

    /**
     * @return the e_principal
     */
    public double getE_principal() {
        return e_principal;
    }

    /**
     * @param e_principal the e_principal to set
     */
    public void setE_principal(double e_principal) {
        this.e_principal = Math.round((e_principal) * 100.0) / 100.0;
    }

    /**
     * @return the e_period
     */
    public int getE_period() {
        return e_period;
    }

    /**
     * @param e_period the e_period to set
     */
    public void setE_period(int e_period) {
        this.e_period = e_period;
    }
    
    public void printInfo(){
        System.out.print ("EMI "+ getEmi()+" ");
        System.out.print ("Interest Part "+ getIntPart() +" ");
        System.out.print ("Principal Part "+getPrnPart()+ " ");
        System.out.println("Remaining Period "+ getE_period());
    }

    /**
     * @return the e_count
     */
    public int getE_count() {
        return e_count;
    }

    /**
     * @param e_count the e_count to set
     */
    public void setE_count(int e_count) {
        this.e_count = e_count;
    }

    /**
     * @return the e_principalOpt
     */
    public double getE_principalOpt() {
        return e_principalOpt;
    }

    /**
     * @param e_principalOpt the e_principalOpt to set
     */
    public void setE_principalOpt(double e_principalOpt) {
        this.e_principalOpt = Math.round((e_principalOpt) * 100.0) / 100.0;
    }

    /**
     * @return the _emiOpt
     */
    public double getEmiOpt() {
        return _emiOpt;
    }

    /**
     * @param _emiOpt the _emiOpt to set
     */
    public void setEmiOpt(double _emiOpt) {
        this._emiOpt = Math.round((_emiOpt) * 100.0) / 100.0;
    }

    /**
     * @return the intPartOpt
     */
    public double getIntPartOpt() {
        return intPartOpt;
    }

    /**
     * @param intPartOpt the intPartOpt to set
     */
    public void setIntPartOpt(double intPartOpt) {
        this.intPartOpt = Math.round((intPartOpt) * 100.0) / 100.0;
    }

    /**
     * @return the prnPartOpt
     */
    public double getPrnPartOpt() {
        return prnPartOpt;
    }

    /**
     * @param prnPartOpt the prnPartOpt to set
     */
    public void setPrnPartOpt(double prnPartOpt) {
        this.prnPartOpt = Math.round((prnPartOpt) * 100.0) / 100.0;
    }
}
