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
    public EmployeeDto saveEmployee(@RequestBody EmployeeDto applicantDto){
       return employeeService.createEmployee(applicantDto);
    }
    
    @GetMapping()
    public List<EmployeeDto> getAllEEmployees(){
        return employeeService.getAllEmployees();
        
    }
    
    @GetMapping("/returnEmployee/{id}")
    public EmployeeDto returnEmployee(@PathVariable("id") long employeeId){
        return employeeService.getEmployeeById(employeeId);
        
    }
    
    @PostMapping("/updateEmployee/{id}")
    public EmployeeDto updateEmployee(@PathVariable("id") long employeeId, @RequestBody EmployeeDto applicantDto){
        return employeeService.updateEmployee(employeeId, applicantDto);
    }
    
    @GetMapping("/deleteEmployee/{id}")
    public Response deleteEmployee(@PathVariable("id") long employeeId){
        if(employeeService.deleteEmployee(employeeId)){
            return Response.SUCCESSFUL;
        }else{
            return Response.FAILED;
        }
        
    }
    
    
    
}
