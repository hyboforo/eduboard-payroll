/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data access class that returns and employee and their pay details
 * @author hybof
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePayDetailsDto {
    
    private String firstName;
    private String lastName;
    private double grossSalary;
    private int payeeRate;
    private int tierRate;
    private int tierTwoRate;
    private int tierThreeRate;
    private String bank;
    private long employeeId;
    
}
