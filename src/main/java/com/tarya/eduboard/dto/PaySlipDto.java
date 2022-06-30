/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *Data access class for Payslips
 * @author hybof
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaySlipDto {
    
    private long id;
    private String date;
    private double tireOneContribution;
    private double tierTwoContribution;
    private double tierThreeContribution;
    private double payeeContributions;
    private double totalDeductions;
    private double netSalary;
    private long employeeId; 
    
}
