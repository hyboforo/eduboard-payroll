/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.service;


import com.tarya.eduboard.dto.EmployeeDto;
import java.util.List;

/**
 * Applicant interface to enforce the creation of needed operations and also support different implementations
 * @author hybof
 */
public interface EmployeeService {
    
    EmployeeDto createEmployee(EmployeeDto newEmployee);
    EmployeeDto updateEmployee(long employeeId, EmployeeDto updateEmployee);
    EmployeeDto getEmployeeById(long employeeId);
    boolean deleteEmployee(long employeeId);
    List<EmployeeDto> getAllEmployees();
    
}
