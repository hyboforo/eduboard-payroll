/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.controller;

import com.tarya.eduboard.dto.PaySlipDto;
import com.tarya.eduboard.service.PaySlipService;
import com.tarya.eduboard.utils.OperationResult;
import com.tarya.eduboard.utils.Response;
import com.tarya.eduboard.utils.ResponseContructor;
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
    public ResponseEntity<OperationResult> getAllPaySlips() {
        ResponseContructor responseContructor = new ResponseContructor();
        List<PaySlipDto> allPaySlips = paySlipService.getAllPaySlips();
        if (allPaySlips == null) {
            OperationResult response = responseContructor.ContructResponse(Response.OPERATION_FAILED, allPaySlips);
            return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
        }
        OperationResult response = responseContructor.ContructResponse(Response.OPERATION_SUCCESSFUL, allPaySlips);
        return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
    }

    @GetMapping("/returnEmployeePaySlip/{id}")
    public ResponseEntity<OperationResult> employeePaySlip(@PathVariable("id") long employeeId) {
        ResponseContructor responseContructor = new ResponseContructor();
        PaySlipDto paySlipByEmployeeId = paySlipService.getPaySlipByEmployeeId(employeeId);
        if (paySlipByEmployeeId == null) {
            OperationResult response = responseContructor.ContructResponse(Response.OPERATION_FAILED, paySlipByEmployeeId);
            return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
        }
        OperationResult response = responseContructor.ContructResponse(Response.OPERATION_SUCCESSFUL, paySlipByEmployeeId);
        return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
    }

    @GetMapping("/payAllEmployees")
    public ResponseEntity<OperationResult> payAllEmployee() {
        ResponseContructor responseContructor = new ResponseContructor();
        List<PaySlipDto> createdPaySlips = paySlipService.createPaySlips();
        if (createdPaySlips == null) {
            OperationResult response = responseContructor.ContructResponse(Response.OPERATION_FAILED, createdPaySlips);
            return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
        }
        OperationResult response = responseContructor.ContructResponse(Response.OPERATION_SUCCESSFUL, createdPaySlips);
        return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
    }

    @GetMapping("/payEmployee/{id}")
    public ResponseEntity<OperationResult> payEmployee(@PathVariable("id") long employeeId) {
         ResponseContructor responseContructor = new ResponseContructor();
        PaySlipDto createdPaySlip = paySlipService.createPaySlip(employeeId);
        if (createdPaySlip == null) {
             OperationResult response = responseContructor.ContructResponse(Response.OPERATION_FAILED, createdPaySlip);
            return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
        }
        OperationResult response = responseContructor.ContructResponse(Response.OPERATION_SUCCESSFUL, createdPaySlip);
        return new ResponseEntity<OperationResult>(response, HttpStatus.OK);

    }

}
