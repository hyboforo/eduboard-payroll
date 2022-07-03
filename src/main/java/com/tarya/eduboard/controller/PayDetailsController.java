/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.controller;

import com.tarya.eduboard.dto.PayDetailsDto;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tarya.eduboard.service.PayDetailsService;
import com.tarya.eduboard.utils.OperationResult;
import com.tarya.eduboard.utils.Response;
import com.tarya.eduboard.utils.ResponseContructor;

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

    @PostMapping("/savePayDetails/{id}")
    public ResponseEntity<OperationResult> savePayDetails(@PathVariable("id") long employeeId, @RequestBody PayDetailsDto newPayDetails) {
        ResponseContructor responseContructor = new ResponseContructor();
        PayDetailsDto createdPayDetails = payDetailsService.createPayDetails(employeeId, newPayDetails);
        if (createdPayDetails == null) {
            OperationResult response = responseContructor.ContructResponse(Response.OPERATION_FAILED, createdPayDetails);
            return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
        }
        OperationResult response = responseContructor.ContructResponse(Response.OPERATION_SUCCESSFUL, createdPayDetails);
        return new ResponseEntity<OperationResult>(response, HttpStatus.CREATED);
    }

    @GetMapping("/returnEmployeePayDetails/{id}")
    public ResponseEntity<OperationResult> returnEmployeePayDetails(@PathVariable("id") long employeeId) {
        ResponseContructor responseContructor = new ResponseContructor();
        PayDetailsDto payDetailsByEmployeeId = payDetailsService.getPayDetailsByEmployeeId(employeeId);
        if (payDetailsByEmployeeId == null) {
            OperationResult response = responseContructor.ContructResponse(Response.OPERATION_FAILED, payDetailsByEmployeeId);
            return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
        }
        OperationResult response = responseContructor.ContructResponse(Response.OPERATION_SUCCESSFUL, payDetailsByEmployeeId);
        return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
    }

    @PutMapping("/updateEmployeePayDetails/{id}")
    public ResponseEntity<OperationResult> updateEmployeePayDetails(@PathVariable("id") long employeeId, @RequestBody PayDetailsDto updatePayDetails) {
        ResponseContructor responseContructor = new ResponseContructor();
        PayDetailsDto updatedEmployeePayDetails = payDetailsService.updateEmployeePayDetails(employeeId, updatePayDetails);
        if (updatedEmployeePayDetails == null) {
            OperationResult response = responseContructor.ContructResponse(Response.OPERATION_FAILED, updatedEmployeePayDetails);
            return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
        }
        OperationResult response = responseContructor.ContructResponse(Response.OPERATION_SUCCESSFUL, updatedEmployeePayDetails);
        return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<OperationResult> returnAllPayDetails() {
        ResponseContructor responseContructor = new ResponseContructor();
        List<PayDetailsDto> allPayDetails = payDetailsService.getAllPayDetails();
        if(allPayDetails == null){
            OperationResult response = responseContructor.ContructResponse(Response.OPERATION_FAILED, allPayDetails);
            return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
        }
        OperationResult response = responseContructor.ContructResponse(Response.OPERATION_SUCCESSFUL, allPayDetails);
        return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
    }

}
