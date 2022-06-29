/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.service;

import com.tarya.eduboard.dto.EmployeePayDetailsDto;
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
public class PaySlipServiceImpl implements PaySlipService{
    
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
    public PaySlipDto createPaySlip(long Id) {
        PaySlip paySlip = new PaySlip();
        PaySlipDto savedPaySlip = null;

        PayDetailsDto employeePayDetails = payDetailsService.getPayDetailsByEmployeeId(Id);
        Map<String, Double> returnDeductionsAndNetPay = NetPayCalculator.returnDeductionsAndNetPay(employeePayDetails.getGrossSalary(), employeePayDetails.getTierRate(), employeePayDetails.getTierTwoRate(), 
                employeePayDetails.getTierThreeRate(), employeePayDetails.getPayeeRate());
        paySlip.setId(sequenceGeneratorService.generateSequence(PaySlip.SEQUENCE_NAME));
        paySlip.setDate(FormatDate.returnFormatedDate());
        paySlip.setEmployeeId(Id);
        paySlip.setTireOneContribution(returnDeductionsAndNetPay.get("tier1"));
        paySlip.setTierTwoContribution(returnDeductionsAndNetPay.get("tier2"));
        paySlip.setTierThreeContribution(returnDeductionsAndNetPay.get("tier3"));
        paySlip.setTotalDeductions(returnDeductionsAndNetPay.get("totalDeductions"));
        paySlip.setNetSalary(returnDeductionsAndNetPay.get("netPay"));
        try{
            savedPaySlip = DtoMapper.toDto(paySlipRepository.save(paySlip), PaySlipDto.class);
        }catch(Exception ex){
            log.error("Failed to save Pay Slip "+ex.getLocalizedMessage());
        }
        return savedPaySlip;
    }

    @Override
    public List<PaySlipDto> getAllPaySlips() {
        List<PaySlipDto> paySlipDtos = DtoMapper.toDtoList(paySlipRepository.findAll(), PaySlipDto.class);
        return paySlipDtos;
    }

    @Override
    public PaySlipDto getPaySlipByEmployeeId(long Id) {
        PaySlipDto paySlipDto = DtoMapper.toDto(paySlipRepository.findById(Id), PaySlipDto.class);
        return paySlipDto;
    }

    
}
