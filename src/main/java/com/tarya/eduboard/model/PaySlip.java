/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author hybof
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pay_slips")
public class PaySlip {

    @Transient
    public static final String SEQUENCE_NAME = "pay_slip_sequence";

    @Id
    private long Id;
    private Date date;
    @Field("tier1_contribution")
    private double tireOneContribution;
    @Field("tier2_contribution")
    private double tierTwoContribution;
    @Field("tier3_contribution")
    private double tierThreeContribution;
    @Field("total_dediuctions")
    private double totalDeductions;
    @Field("net_pay")
    private double netSalary;
    @Field("employee_id")
    private long employeeId;

}
