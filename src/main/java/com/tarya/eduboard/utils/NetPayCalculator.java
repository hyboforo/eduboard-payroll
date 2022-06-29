/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.utils;

/**
 *
 * @author hybof
 */
public class NetPayCalculator {
    
    
    /**
     * Method to calculate net pay for an Employee
     * @param grossPay  Total amount before deduction
     * @param tier1Rate deductions for tier 1
     * @param tier2Rate deductions for tier 2
     * @param tier3Rate deductions for tier3
     * @param payeeRate deductions for payee
     * @return 
     */
    public double returnNetPay(double grossPay, int tier1Rate, int tier2Rate, int tier3Rate, int payeeRate){
        double temp = grossPay;
        double payeeAmount = 0;
        double tier1Amount = 0;
        double tier2Amount = 0;
        double tier3Amount = 0;
        tier1Amount = temp *(tier1Rate/100);
        temp = temp - tier1Amount;
        tier2Amount = temp * (tier2Rate/100);
        temp = temp - tier2Amount;
        tier3Amount = temp * (tier3Rate/100);
        temp = temp - tier3Amount;
        payeeAmount = temp * (payeeRate/100);
        temp = temp - payeeAmount;
        return temp;   
    }
    
}
