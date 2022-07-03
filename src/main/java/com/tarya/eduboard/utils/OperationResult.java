/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author hybof
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationResult {
    private Response operationalResponse;
    Object responseBody;
    
    
}
