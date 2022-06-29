/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.controller;

import com.tarya.eduboard.dto.EmployeeDto;
import com.tarya.eduboard.dto.EmployeePayDetailsDto;
import com.tarya.eduboard.dto.PayDetailsDto;
import com.tarya.eduboard.service.EmployeeService;
import com.tarya.eduboard.service.PayDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hybof
 */
@Slf4j
@RestController
@RequestMapping("payDetails")
public class PayDetailsController {
    
    @Autowired
    private PayDetailsService payDetailsService;
    
    @Autowired
    private EmployeeService employeeService;
    
    @PostMapping("/savedPayDetails")
    public PayDetailsDto savePayDetails(@RequestBody PayDetailsDto newPayDetails){
        return payDetailsService.createPayDetails(newPayDetails);
    }
    
    @GetMapping("/returnEmployeePayDetails/{id}")
    public EmployeePayDetailsDto returnEmployeePayDetails(@PathVariable("id") long Id){
        
    }
    
    public EmployeePayDetailsDto createEmployeePayDetails(long Id){
        EmployeePayDetailsDto employeePayDetailsDto = new EmployeePayDetailsDto();
        PayDetailsDto payDetailsByEmployeeId = payDetailsService.getPayDetailsByEmployeeId(Id);
        EmployeeDto employeeById = employeeService.getEmployeeById(Id);
    }
    
}
