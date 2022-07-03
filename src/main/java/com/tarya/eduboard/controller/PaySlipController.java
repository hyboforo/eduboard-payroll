/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.controller;

import com.tarya.eduboard.dto.PaySlipDto;
import com.tarya.eduboard.service.PaySlipService;
import com.tarya.eduboard.utils.Response;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<PaySlipDto>> getAllPaySlips(){
        List<PaySlipDto> allPaySlips = paySlipService.getAllPaySlips();
        return new ResponseEntity<List<PaySlipDto>>(allPaySlips,HttpStatus.OK);
    }
    
    @GetMapping("/returnEmployeePaySlip/{id}")
    public ResponseEntity<PaySlipDto> employeePaySlip(@PathVariable("id") long employeeId){
        PaySlipDto paySlipByEmployeeId = paySlipService.getPaySlipByEmployeeId(employeeId);
        if(paySlipByEmployeeId == null){
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  
        }
        return new ResponseEntity<PaySlipDto>(paySlipByEmployeeId,HttpStatus.OK);
    }
    
    @GetMapping("/payAllEmployees")
    public ResponseEntity<List<PaySlipDto>> payAllEmployee(){
        List<PaySlipDto> createdPaySlips = paySlipService.createPaySlips();
        if(createdPaySlips == null){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }
        return new ResponseEntity<List<PaySlipDto>>(createdPaySlips,HttpStatus.OK);
    }
    
    @GetMapping("/payEmployee/{id}")
    public ResponseEntity<PaySlipDto> payEmployee(@PathVariable("id") long employeeId){
        PaySlipDto createdPaySlip = paySlipService.createPaySlip(employeeId);
        if(createdPaySlip == null){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }
        return new ResponseEntity<PaySlipDto>(createdPaySlip,HttpStatus.OK);
        
    }
    
    
}
