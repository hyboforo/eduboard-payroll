/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.repository;

import com.tarya.eduboard.model.PayDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author hybof
 */
public interface PayDetailsRepository extends MongoRepository<PayDetails, Long>{
    
    PayDetails getPayDetailsByEmployeeId(long Id);
        
    
    
}
