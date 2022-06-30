/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.service;

import com.tarya.eduboard.dto.PayDetailsDto;
import java.util.List;

/**
 *
 * @author hybof
 */
public interface PayDetailsService {
    
    PayDetailsDto createPayDetails(long employeeId, PayDetailsDto newPayDetails);
    List<PayDetailsDto> getAllPayDetails ();
    PayDetailsDto getPayDetailsByEmployeeId(long employeeId);
    PayDetailsDto updateEmployeePayDetails(long employeeId, PayDetailsDto updatePayDetails);
    
    
}
