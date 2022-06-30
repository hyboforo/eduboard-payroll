/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.controller;

import com.tarya.eduboard.dto.EmployeeDto;
import com.tarya.eduboard.dto.PayDetailsDto;
import com.tarya.eduboard.service.EmployeeService;
import com.tarya.eduboard.service.PayDetailsService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PayDetailsDto> savePayDetails(@PathVariable("id") long employeeId, @RequestBody PayDetailsDto newPayDetails){
        PayDetailsDto createdPayDetails = payDetailsService.createPayDetails(employeeId, newPayDetails);
        return new ResponseEntity<PayDetailsDto>(createdPayDetails,HttpStatus.CREATED);
    }
    
    @GetMapping("/returnEmployeePayDetails/{id}")
    public ResponseEntity<PayDetailsDto> returnEmployeePayDetails(@PathVariable("id") long employeeId){
        PayDetailsDto payDetailsByEmployeeId = payDetailsService.getPayDetailsByEmployeeId(employeeId);
        return new ResponseEntity<PayDetailsDto>(payDetailsByEmployeeId,HttpStatus.OK);
    }
    
    @PostMapping("/updateEmployeePayDetails/{id}")
    public ResponseEntity<PayDetailsDto> updateEmployeePayDetails(@PathVariable("id") long employeeId, @RequestBody PayDetailsDto updatePayDetails){
        PayDetailsDto updatedEmployeePayDetails = payDetailsService.updateEmployeePayDetails(employeeId, updatePayDetails);
        return new ResponseEntity<PayDetailsDto>(updatedEmployeePayDetails,HttpStatus.OK);
    }
    
    @GetMapping()
    public ResponseEntity<List<PayDetailsDto>> returnAllPayDetails(){
        List<PayDetailsDto> allPayDetails = payDetailsService.getAllPayDetails();
        return new ResponseEntity<List<PayDetailsDto>>(allPayDetails,HttpStatus.OK);
    }
    
    
}
