/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.controller;

import com.tarya.eduboard.dto.PayDetailsDto;
import com.tarya.eduboard.service.EmployeeService;
import com.tarya.eduboard.service.PayDetailsService;
import java.util.List;
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
    
    @PostMapping("/{id}")
    public PayDetailsDto savePayDetails(@PathVariable("id") long employeeId, @RequestBody PayDetailsDto newPayDetails){
        return payDetailsService.createPayDetails(employeeId, newPayDetails);
    }
    
    @GetMapping("/returnEmployeePayDetails/{id}")
    public PayDetailsDto returnEmployeePayDetails(@PathVariable("id") long employeeId){
        return payDetailsService.getPayDetailsByEmployeeId(employeeId);
    }
    
    @PostMapping("/updateEmployeePayDetails/{id}")
    public PayDetailsDto updateEmployeePayDetails(@PathVariable("id") long employeeId, @RequestBody PayDetailsDto updatePayDetails){
        return payDetailsService.updateEmployeePayDetails(employeeId, updatePayDetails);
    }
    
    @GetMapping()
    public List<PayDetailsDto> returnAllPayDetails(){
       return  payDetailsService.getAllPayDetails();
    }
    
    
}
