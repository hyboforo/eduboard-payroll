/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Entity class for an applicant We will use Lombok to reduce the amount of code
 * we have to write
 *
 * @author hybof
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "employees")
public class Employee {
    
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;
    @Field("first_name")
    private String firstName;
    @Field("last_name")
    private String lastName;
    // we will make email as a unique entry
    @Indexed(unique = true)
    private String email;
    // we will make phone as a unique entry
    @Indexed(unique = true)
    private String role;


    // custome toString method for returning of data 
    @Override
    public String toString() {
        return "Applicant [id:" + id + ", First Name:" + firstName + ", Last Name:" + lastName + ", Email:" + email + ", Role:" + role + "]";
    }
}
