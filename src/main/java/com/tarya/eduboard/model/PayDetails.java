/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Entity class for pay details such as tax rate tier 1 pension rate tier 2
 * pension rate tier 3 pension rate gross pay
 *
 * @author hybof
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pay_details")
public class PayDetails {

    @Transient
    public static final String SEQUENCE_NAME = "pay_details_sequence";

    @Id
    private long id;
    @Field("employee_id")
    private long employeeId;
    @Field("first_name")
    private String employeeFirstName;
    @Field("last_name")
    private String employeeLastName;
    @Field("gross_pay")
    private double grossSalary;
    @Field("payee_rate")
    private int payeeRate;
    @Field("tier1_rate")
    private int tierOneRate;
    @Field("tier2_rate")
    private int tierTwoRate;
    @Field("tier3_rate")
    private int tierThreeRate;
    
    private String bank;

}
