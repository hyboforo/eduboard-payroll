/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.utils;

/**
 *
 * @author hybof
 */
public class ResponseContructor {
    
    public OperationResult ContructResponse(Response response, Object dto){
        OperationResult OperationResult = new OperationResult();
        OperationResult.setOperationalResponse(response);
        OperationResult.setResponseBody(dto);
        return OperationResult;
    }

    
}
