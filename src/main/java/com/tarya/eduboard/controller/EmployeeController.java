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
import com.tarya.eduboard.utils.Response;
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
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto createdEmployee = employeeService.createEmployee(employeeDto);
        if(createdEmployee == null){
            return new ResponseEntity<EmployeeDto>(createdEmployee,HttpStatus.OK);
        }
        return new ResponseEntity<EmployeeDto>(createdEmployee,HttpStatus.CREATED);
    }
    
    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEEmployees(){
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees();
        return new ResponseEntity<List<EmployeeDto>>(allEmployees,HttpStatus.OK);
        
    }
    
    @GetMapping("/returnEmployee/{id}")
    public ResponseEntity<EmployeeDto> returnEmployee(@PathVariable("id") long employeeId){
        EmployeeDto returnedEmployee = employeeService.getEmployeeById(employeeId);
        if(returnedEmployee == null){
          return new ResponseEntity<EmployeeDto>(returnedEmployee,HttpStatus.OK);  
        }
        return new ResponseEntity<EmployeeDto>(returnedEmployee,HttpStatus.OK);
        
    }
    
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") long employeeId, @RequestBody EmployeeDto applicantDto){
        EmployeeDto updatedEmployee = employeeService.updateEmployee(employeeId, applicantDto);
        if(updatedEmployee == null){
           return new ResponseEntity<EmployeeDto>(updatedEmployee,HttpStatus.OK); 
        }
        return new ResponseEntity<EmployeeDto>(updatedEmployee,HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<Response> deleteEmployee(@PathVariable("id") long employeeId){
        if(employeeService.deleteEmployee(employeeId)){
            return new ResponseEntity<Response>(Response.SUCCESSFUL,HttpStatus.OK);
        }else{
            return new ResponseEntity<Response>(Response.FAILED,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    
    
}
