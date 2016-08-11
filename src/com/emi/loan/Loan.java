/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emi.loan;

/**
 *E =( P * r * (1 + r)^n) / ( (1 + r) ^n - 1)
 * 
 * @author Arun
 */
public class Loan {
    
    private double _principal;
    private double _roi;
    private int _period;
    private double _prepaidAmt;
        private double _principalOpt;
    
    
    public Loan(double _principal, double _roi, int _period){
        this._principal = _principal;
        this._period = _period;
        this._roi = _roi;
    }
    
    
      public Loan(double _principal, double _roi, int _period, double _prepaidAmt){
        this._principal = _principal;
        this._principalOpt = _principal;
        this._period = _period;
        this._roi = _roi;
        this._prepaidAmt = _prepaidAmt;
    }

    /**
     * @return the _principal
     */
    public double getPrincipal() {
        return _principal;
    }

    /**
     * @param principal the _principal to set
     */
    public void setPrincipal(double principal) {
        this._principal = principal;
    }

    /**
     * @return the _roi
     */
    public double getRoi() {
        return _roi;
    }

    /**
     * @param roi the _roi to set
     */
    public void setRoi(double roi) {
        this._roi = roi;
    }

    /**
     * @return the _period
     */
    public int getPeriod() {
        return _period;
    }

    /**
     * @param period the _period to set
     */
    public void setPeriod(int period) {
        this._period = period;
    }

    /**
     * @return the _prepaidAmt
     */
    public double getPrepaidAmt() {
        return _prepaidAmt;
    }

    /**
     * @param _prepaidAmt the _prepaidAmt to set
     */
    public void setPrepaidAmt(double _prepaidAmt) {
        this._prepaidAmt = _prepaidAmt;
    }

    /**
     * @return the _principalOpt
     */
    public double getPrincipalOpt() {
        return _principalOpt;
    }

    /**
     * @param _principalOpt the _principalOpt to set
     */
    public void setPrincipalOpt(double _principalOpt) {
        this._principalOpt = _principalOpt;
    }
    
}
