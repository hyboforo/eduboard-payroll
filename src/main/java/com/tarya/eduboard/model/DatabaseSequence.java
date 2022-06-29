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
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class to handle auto incremental sequence for other collection
 * this is to give us the functionality of an auto incremental primary key
 * @author hybof
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "database_sequences")
public class DatabaseSequence {
    
    @Id
    private String id;

    private long seq;
    
}
