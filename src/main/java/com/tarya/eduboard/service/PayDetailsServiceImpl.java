/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.service;

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
public class PayDetailsServiceImpl implements PayDetailsService{
    
    @Autowired
    private PayDetailsRepository payDetailsRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Transactional
    @Override
    public PayDetailsDto createPayDetails(PayDetailsDto newPayDetails) {
        PayDetails savePayDetails = null;
       newPayDetails.setId(sequenceGeneratorService.generateSequence(PayDetails.SEQUENCE_NAME));
        PayDetails payDetails = DtoMapper.fromDto(newPayDetails, PayDetails.class);
        try{
            savePayDetails = payDetailsRepository.save(payDetails);
        }catch(Exception ex){
            log.error("Failed to Save pay details "+ex.getLocalizedMessage());
        }
        return DtoMapper.toDto(savePayDetails, PayDetailsDto.class);
    }

    @Override
    public List<PayDetailsDto> getAllPayDetails() {
        List<PayDetailsDto> payDetails = DtoMapper.toDtoList(payDetailsRepository.findAll(), PayDetailsDto.class);
        return payDetails;
    }

    @Override
    public PayDetailsDto getPayDetailsByEmployeeId(long Id) {
       PayDetailsDto payDetail = DtoMapper.toDto(payDetailsRepository.findById(Id), PayDetailsDto.class);
        return payDetail; 
    }
    
}
