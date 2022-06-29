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
    public EmployeeDto returnEmployee(@PathVariable("id") long Id){
        return employeeService.getEmployeeById(Id);
        
    }
    
    @PostMapping("/updateEmployee/{id}")
    public EmployeeDto updateEmployee(@PathVariable("id") long Id, @RequestBody EmployeeDto applicantDto){
        return employeeService.updateEmployee(Id, applicantDto);
    }
    
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") long Id){
        if(employeeService.deleteEmployee(Id)){
            return "Employee with ID "+ Id +" has been deleted";
        }else{
            return "Failed to delete Employee with ID "+ Id;
        }
        
    }
    
    
    
}
