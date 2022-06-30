/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.service;


import com.tarya.eduboard.dto.EmployeeDto;
import com.tarya.eduboard.model.Employee;
import com.tarya.eduboard.utils.DtoMapper;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tarya.eduboard.repository.EmployeeRepository;

/**
 *
 * @author hybof
 */
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    /**
     * Method to handle the creation of a new Application
     *
     * @param newApplicant The new applicant that has to be created or saved
     * @return Employee the saved applicant 
     * @see Employee
     */
    @Override
    public EmployeeDto createEmployee(EmployeeDto newEmployee) {
        newEmployee.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
        Employee employee = DtoMapper.fromDto(newEmployee, Employee.class);
        Employee savedEmployee = null;
        try {
            savedEmployee = employeeRepository.save(employee);
        } catch (Exception ex) {
            log.error("Failed to save Applicant " + ex.getLocalizedMessage());
        }
        return DtoMapper.toDto(savedEmployee, EmployeeDto.class);

    }

    /**
     * Method to update Employee
     *
     * @param employeeId the ID of the Employee to be updated
     * @param updateApplicant the Employee object that will replace the existing applicant
     * @return updated Employee returns the updated Employee
     */
    @Override
    public EmployeeDto updateEmployee(long employeeId, EmployeeDto newEmployee) {
        EmployeeDto updateEmployeeDto = null;
        Optional<Employee> returnedApplicant = employeeRepository.findById(employeeId);
        if (returnedApplicant.isPresent()) {
            Employee updateEmployee = DtoMapper.fromDto(newEmployee, Employee.class);
            Employee originalEmployee = returnedApplicant.get();
            originalEmployee.setFirstName(updateEmployee.getFirstName());
            originalEmployee.setLastName(updateEmployee.getLastName());
            originalEmployee.setRole(updateEmployee.getRole());
            originalEmployee.setEmail(updateEmployee.getEmail());
            try {
                Employee employee = employeeRepository.save(originalEmployee);
                updateEmployeeDto = DtoMapper.toDto(employee, EmployeeDto.class);
            } catch (Exception ex) {
                log.error("Failed to update Employee with ID " + employeeId + " " + ex.getLocalizedMessage());
            }

        } else {
            log.error("Failed to find Employee with Id " + employeeId);
            updateEmployeeDto = null;
        }
        return updateEmployeeDto;
    }

    /**
     * Method to find applicant by ID
     *
     * @param employeeId the ID of the Employee to be updated
     * @return applicant the Employee that was found by the Id
     */
    @Override
    public EmployeeDto getEmployeeById(long employeeId) {
        EmployeeDto employeeDto = null;
        Optional<Employee> returnedEmployee = employeeRepository.findById(employeeId);
        if (returnedEmployee.isPresent()) {
            return employeeDto = DtoMapper.toDto(returnedEmployee.get(), EmployeeDto.class);
        }
        log.error("Failed to find Applicant with ID " + employeeId);
        return employeeDto;
    }

    /**
     * method to return all applicants
     *
     * @return all Applicants
     */
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> allEmployeeDto = DtoMapper.toDtoList(employeeRepository.findAll(), EmployeeDto.class);
        return allEmployeeDto;
    }

    /**
     * method to delete an applicant
     *
     * @param employeeId the ID of the Employee to be updated
     * @return returns a boolean
     */
    @Override
    public boolean deleteEmployee(long employeeId) {
        try {
            employeeRepository.deleteById(employeeId);
            return true;
        } catch (Exception ex) {
            log.error("Failed to delete Employee with Id "+employeeId+" "+ex.getLocalizedMessage());
            return false;
        }

    }

}
