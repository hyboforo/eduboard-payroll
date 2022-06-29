/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.service;

import com.tarya.eduboard.model.PaySlip;
import java.util.List;

/**
 *
 * @author hybof
 */
public interface PaySlipService {
    PaySlip createPaySlip(PaySlip newPaySlip);
    List<PaySlip> getAllPaySlips();
    PaySlip getPaySlipByEmployeeId(long Id);
}
