/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transport object class to decouple the data sent to a caller from our applicant entity
 * for this example, the DTO is identical to the actually entity class
 * @author hybof
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    
}
