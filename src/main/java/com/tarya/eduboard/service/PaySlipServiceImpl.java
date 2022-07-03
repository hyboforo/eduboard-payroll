/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.service;

import com.tarya.eduboard.dto.EmployeeDto;
import com.tarya.eduboard.dto.PayDetailsDto;
import com.tarya.eduboard.dto.PaySlipDto;
import com.tarya.eduboard.model.PaySlip;
import com.tarya.eduboard.repository.PaySlipRepository;
import com.tarya.eduboard.utils.DtoMapper;
import com.tarya.eduboard.utils.FormatDate;
import com.tarya.eduboard.utils.NetPayCalculator;
import java.util.List;
import java.util.Map;
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
public class PaySlipServiceImpl implements PaySlipService {

    @Autowired
    private PaySlipRepository paySlipRepository;
    @Autowired
    private PayDetailsService payDetailsService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Transactional
    @Override
    public PaySlipDto createPaySlip(long employeeId) {
        PaySlipDto savedPaySlip = null;
        PaySlipServiceImpl paysePaySlipServiceImpl = new PaySlipServiceImpl();
        PayDetailsDto employeePayDetails = payDetailsService.getPayDetailsByEmployeeId(employeeId);
        if (employeePayDetails == null) {
            log.info("Cannot process pay, Empoyee with ID " + employeeId + " cannot be found");
            return null;
        }
        PaySlip paySlip = paysePaySlipServiceImpl.createPaySlipObject(employeePayDetails);
        paySlip.setId(sequenceGeneratorService.generateSequence(PaySlip.SEQUENCE_NAME));
        try {
            savedPaySlip = DtoMapper.toDto(paySlipRepository.save(paySlip), PaySlipDto.class);
        } catch (Exception ex) {
            log.error("Failed to save Pay Slip " + ex.getLocalizedMessage());
            return savedPaySlip;
        }
        return savedPaySlip;
    }

    @Override
    public List<PaySlipDto> getAllPaySlips() {
        List<PaySlipDto> paySlipDtos = null;
        try {
            paySlipDtos = DtoMapper.toDtoList(paySlipRepository.findAll(), PaySlipDto.class);
        } catch (Exception ex) {
            log.error("Failed to find pay details " + ex.getLocalizedMessage());
            return paySlipDtos;
        }
        return paySlipDtos;
    }

    @Override
    public PaySlipDto getPaySlipByEmployeeId(long Id) {
        PaySlipDto paySlipDto = null;
        try {
            paySlipDto = DtoMapper.toDto(paySlipRepository.findBy(Id), PaySlipDto.class);
        } catch (Exception ex) {
            log.error("Find to find pay slip " + ex.getLocalizedMessage());
            return paySlipDto;
        }
        return paySlipDto;
    }

    @Override
    public List<PaySlipDto> createPaySlips() {
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees();
        if (allEmployees == null) {
            log.info("Cannot process pay, No employee found");
            return null;
        }
        for (EmployeeDto allEmployee : allEmployees) {
            log.info("Employee ID is " + allEmployee.getId());
            PaySlipServiceImpl paysePaySlipServiceImpl = new PaySlipServiceImpl();
            PayDetailsDto employeePayDetails = payDetailsService.getPayDetailsByEmployeeId(allEmployee.getId());
            PaySlip paySlip = null;
            try {
                paySlip = paysePaySlipServiceImpl.createPaySlipObject(employeePayDetails);
                paySlip.setId(sequenceGeneratorService.generateSequence(PaySlip.SEQUENCE_NAME));
            } catch (Exception ex) {
                log.error("Employee with ID "+allEmployee.getId()+ " has not been cleared for payment");
            }
            
            try {
                paySlipRepository.save(paySlip);
            } catch (Exception ex) {
                log.error("Failed to save Pay Slip " + ex.getLocalizedMessage());
            }
        }
        List<PaySlipDto> payslips = null;
        try {
            payslips = DtoMapper.toDtoList(paySlipRepository.findAll(), PaySlipDto.class);
        } catch (Exception ex) {
            log.error("Failed to retrieve pay slips "+ex.getLocalizedMessage());
            return null;
        }
        return payslips;
    }

    public PaySlip createPaySlipObject(PayDetailsDto employeePayDetails) {
        PaySlip paySlip = new PaySlip();
        Map<String, Double> returnDeductionsAndNetPay = NetPayCalculator.returnDeductionsAndNetPay(employeePayDetails.getGrossSalary(), employeePayDetails.getTierOneRate(), employeePayDetails.getTierTwoRate(),
                employeePayDetails.getTierThreeRate(), employeePayDetails.getPayeeRate());

        paySlip.setDate(FormatDate.returnFormatedDate());
        paySlip.setEmployeeId(employeePayDetails.getEmployeeId());
        paySlip.setTireOneContribution(returnDeductionsAndNetPay.get("tier1"));
        paySlip.setTierTwoContribution(returnDeductionsAndNetPay.get("tier2"));
        paySlip.setTierThreeContribution(returnDeductionsAndNetPay.get("tier3"));
        paySlip.setPayeeContributions(returnDeductionsAndNetPay.get("payee"));
        paySlip.setTotalDeductions(returnDeductionsAndNetPay.get("totalDeductions"));
        paySlip.setNetSalary(returnDeductionsAndNetPay.get("netPay"));
        return paySlip;
    }

}
