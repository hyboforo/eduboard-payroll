/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.repository;

import com.tarya.eduboard.model.PayDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author hybof
 */
public interface PayDetailsRepository extends MongoRepository<PayDetails, Long>{
    
    @Query("{'employee_id' : ?0}")
    PayDetails findBy(long Id);
        
    
    
}
