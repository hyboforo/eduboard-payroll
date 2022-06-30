/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.repository;

import com.tarya.eduboard.model.PaySlip;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author hybof
 */
public interface PaySlipRepository extends MongoRepository<PaySlip, Long>{
    
    @Query("{'employee_id' : ?0}")
    PaySlip findBy(long Id);
}
