/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.service;

import com.tarya.eduboard.model.PayDetails;
import java.util.List;

/**
 *
 * @author hybof
 */
public interface payDetailsService {
    
    PayDetails createPayDetails(PayDetails newPayDetails);
    List<PayDetails> getAllPayDetails ();
    PayDetails getPayDetailsByEmployeeId(long Id);
    
}
