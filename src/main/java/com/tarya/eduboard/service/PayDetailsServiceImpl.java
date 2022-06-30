/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.service;

import com.tarya.eduboard.dto.EmployeeDto;
import com.tarya.eduboard.dto.PayDetailsDto;
import com.tarya.eduboard.model.PayDetails;
import com.tarya.eduboard.repository.PayDetailsRepository;
import com.tarya.eduboard.utils.DtoMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hybof
 */
@Slf4j
@Service
public class PayDetailsServiceImpl implements PayDetailsService {
    
    @Autowired
    private PayDetailsRepository payDetailsRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private EmployeeService employeeService;
    
    @Transactional
    @Override
    public PayDetailsDto createPayDetails(long employeeId, PayDetailsDto newPayDetails) {
        PayDetails savePayDetails = null;
        EmployeeDto returnedEmployeeDto = employeeService.getEmployeeById(employeeId);
        if(returnedEmployeeDto == null){
            log.error("Employee with ID "+employeeId+ " was not found. Cannt save Pay Details for Employee");
            return null;
        }
        newPayDetails.setEmployeeId(employeeId);
        newPayDetails.setEmployeeFirstName(returnedEmployeeDto.getFirstName());
        newPayDetails.setEmployeeLastName(returnedEmployeeDto.getLastName());
        newPayDetails.setId(sequenceGeneratorService.generateSequence(PayDetails.SEQUENCE_NAME));
        PayDetails payDetails = DtoMapper.fromDto(newPayDetails, PayDetails.class);
        try {
            savePayDetails = payDetailsRepository.save(payDetails);
        } catch (Exception ex) {
            log.error("Failed to Save pay details " + ex.getLocalizedMessage());
        }
        return DtoMapper.toDto(savePayDetails, PayDetailsDto.class);
    }
    
    @Override
    public List<PayDetailsDto> getAllPayDetails() {
        List<PayDetailsDto> payDetailDtos = DtoMapper.toDtoList(payDetailsRepository.findAll(), PayDetailsDto.class);
        return payDetailDtos;
    }
    
    @Override
    public PayDetailsDto getPayDetailsByEmployeeId(long employeeId) {
        PayDetailsDto payDetailDto = DtoMapper.toDto(payDetailsRepository.findBy(employeeId), PayDetailsDto.class);
        return payDetailDto;        
    }

    @Transactional
    @Override
    public PayDetailsDto updateEmployeePayDetails(long employeeId, PayDetailsDto updatePayDetails) {
        PayDetails updatedPayDetails = null;
        EmployeeDto returnedEmployeeDto = employeeService.getEmployeeById(employeeId);
        if(returnedEmployeeDto == null){
            log.error("Employee with ID "+employeeId+ " was not found. Cannot update Pay Details for Employee");
            return null;
        }
        PayDetails toUpdate = payDetailsRepository.findBy(employeeId);
        toUpdate.setGrossSalary(updatePayDetails.getGrossSalary());
        toUpdate.setPayeeRate(updatePayDetails.getPayeeRate());
        toUpdate.setTierOneRate(updatePayDetails.getTierOneRate());
        toUpdate.setTierTwoRate(updatePayDetails.getTierTwoRate());
        toUpdate.setTierThreeRate(updatePayDetails.getTierThreeRate());
        toUpdate.setBank(updatePayDetails.getBank());
        try {
            updatedPayDetails = payDetailsRepository.save(toUpdate);
        } catch (Exception ex) {
            log.error("Failed to Save pay details " + ex.getLocalizedMessage());
            return null;
        }
        return DtoMapper.toDto(updatedPayDetails, PayDetailsDto.class);
        
    }
    
    
    
}
