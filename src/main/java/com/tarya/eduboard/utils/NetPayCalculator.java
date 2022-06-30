/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.utils;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author hybof
 */
@Slf4j
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
    public static Map<String, Double> returnDeductionsAndNetPay(double grossPay, int tier1Rate, int tier2Rate, int tier3Rate, int payeeRate){
        Map<String, Double> deductionsAndNetPay =  new HashMap<String, Double>();
        double temp = grossPay;
        double payeeAmount = 0;
        double tier1Amount = 0;
        double tier2Amount = 0;
        double tier3Amount = 0;
        tier1Amount = (double)temp * returnRateInDecimal(tier1Rate);
        temp = temp - tier1Amount;
        tier2Amount = (double)temp * returnRateInDecimal(tier2Rate);        
        temp = temp - tier2Amount;
        tier3Amount = (double)temp * returnRateInDecimal(tier3Rate);
        temp = temp - tier3Amount;
        payeeAmount = (double)temp * returnRateInDecimal(payeeRate); 
        temp = temp - payeeAmount;
        double totalDeductions = tier1Amount + tier2Amount + tier3Amount + payeeAmount; 
        deductionsAndNetPay.put("tier1", tier1Amount);
        deductionsAndNetPay.put("tier2", tier2Amount);
        deductionsAndNetPay.put("tier3", tier3Amount);
        deductionsAndNetPay.put("payee", payeeAmount);
        deductionsAndNetPay.put("totalDeductions", totalDeductions);
        deductionsAndNetPay.put("netPay", temp);
        log.info("Net pay "+temp);
        return deductionsAndNetPay;   
    }
    
    public static double returnRateInDecimal(int rate){
        return (double)rate/100;
    }
    
    
}
