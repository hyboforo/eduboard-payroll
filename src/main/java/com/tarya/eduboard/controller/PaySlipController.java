/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.controller;

import com.tarya.eduboard.dto.PaySlipDto;
import com.tarya.eduboard.service.PaySlipService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hybof
 */

@Slf4j
@RestController
@RequestMapping("paySlip")
public class PaySlipController {
    
    @Autowired
    private PaySlipService paySlipService;
    
    @GetMapping()
    public List<PaySlipDto> getAllPaySlips(){
        return paySlipService.getAllPaySlips();
    }
    
    @GetMapping("/returnEmployeePaySlip/{id}")
    public PaySlipDto employeePaySlip(@PathVariable("id") long employeeId){
        return paySlipService.getPaySlipByEmployeeId(employeeId);
    }
    
    @GetMapping("/payAllEmployee")
    public List<PaySlipDto> payAllEmployee(){
       return paySlipService.createPaySlips();
    }
    
    @GetMapping("/payEmployee/{id}")
    public PaySlipDto payEmployee(@PathVariable("id") long employeeId){
        return paySlipService.createPaySlip(employeeId);
    }
    
    
}
