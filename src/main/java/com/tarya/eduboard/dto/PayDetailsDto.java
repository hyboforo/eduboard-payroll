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
 * Data access class for PayDetails
 *
 * @author hybof
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayDetailsDto {

    private long id;
    private long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private double grossSalary;
    private int payeeRate;
    private int tierOneRate;
    private int tierTwoRate;
    private int tierThreeRate;
    private String bank;

}
