/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.service;

import com.tarya.eduboard.dto.EmployeeDto;
import com.tarya.eduboard.dto.PaySlipDto;
import java.util.List;

/**
 *
 * @author hybof
 */
public interface PaySlipService {
    PaySlipDto createPaySlip(long employeeId);
    List<PaySlipDto> getAllPaySlips();
    PaySlipDto getPaySlipByEmployeeId(long employeeId);
    List<PaySlipDto> createPaySlips();
}
