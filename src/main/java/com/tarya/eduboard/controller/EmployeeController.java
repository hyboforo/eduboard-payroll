/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.controller;

import com.tarya.eduboard.dto.EmployeeDto;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tarya.eduboard.service.EmployeeService;
import com.tarya.eduboard.utils.OperationResult;
import com.tarya.eduboard.utils.Response;
import com.tarya.eduboard.utils.ResponseContructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author hybof
 */
@Slf4j
@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/saveEmployee")
    public ResponseEntity<OperationResult> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        ResponseContructor responseContructor = new ResponseContructor();
        EmployeeDto createdEmployee = employeeService.createEmployee(employeeDto);
        if (createdEmployee == null) {
            OperationResult response = responseContructor.ContructResponse(Response.OPERATION_FAILED, createdEmployee);
            return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
        }
        OperationResult response = responseContructor.ContructResponse(Response.OPERATION_SUCCESSFUL, createdEmployee);
        return new ResponseEntity<OperationResult>(response, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<OperationResult> getAllEEmployees() {
        ResponseContructor responseContructor = new ResponseContructor();
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees();
        if (allEmployees == null) {
            OperationResult response = responseContructor.ContructResponse(Response.OPERATION_FAILED, allEmployees);
            return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
        }
        OperationResult response = responseContructor.ContructResponse(Response.OPERATION_SUCCESSFUL, allEmployees);
        return new ResponseEntity<OperationResult>(response, HttpStatus.OK);

    }

    @GetMapping("/returnEmployee/{id}")
    public ResponseEntity<OperationResult> returnEmployee(@PathVariable("id") long employeeId) {
        ResponseContructor responseContructor = new ResponseContructor();
        EmployeeDto returnedEmployee = employeeService.getEmployeeById(employeeId);
        if (returnedEmployee == null) {
            OperationResult response = responseContructor.ContructResponse(Response.OPERATION_FAILED, returnedEmployee);
            return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
        }
        OperationResult response = responseContructor.ContructResponse(Response.OPERATION_SUCCESSFUL, returnedEmployee);
        return new ResponseEntity<OperationResult>(response, HttpStatus.OK);

    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<OperationResult> updateEmployee(@PathVariable("id") long employeeId, @RequestBody EmployeeDto applicantDto) {
        ResponseContructor responseContructor = new ResponseContructor();
        EmployeeDto updatedEmployee = employeeService.updateEmployee(employeeId, applicantDto);
        if (updatedEmployee == null) {
            OperationResult response = responseContructor.ContructResponse(Response.OPERATION_FAILED, updatedEmployee);
            return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
        }
        OperationResult response = responseContructor.ContructResponse(Response.OPERATION_SUCCESSFUL, updatedEmployee);
        return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<OperationResult> deleteEmployee(@PathVariable("id") long employeeId) {
        ResponseContructor responseContructor = new ResponseContructor();
        if (employeeService.deleteEmployee(employeeId)) {
            OperationResult response = responseContructor.ContructResponse(Response.OPERATION_SUCCESSFUL, null);
            return new ResponseEntity<OperationResult>(response, HttpStatus.OK);
        } else {
            OperationResult response = responseContructor.ContructResponse(Response.OPERATION_SUCCESSFUL, null);
            return new ResponseEntity<OperationResult>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
