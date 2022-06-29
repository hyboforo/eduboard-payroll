/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.repository;


import com.tarya.eduboard.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *A repository for creating and access data of an applicant 
 * @author hybof
 */
public interface EmployeeRepository extends MongoRepository<Employee, Long>{
    
   
    
}
