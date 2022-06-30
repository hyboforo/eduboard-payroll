/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarya.eduboard.dto.EmployeeDto;
import com.tarya.eduboard.dto.PayDetailsDto;
import com.tarya.eduboard.service.PayDetailsService;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 * @author hybof
 */

@RunWith(SpringRunner.class)
@WebMvcTest(value = PayDetailsController.class)
public class PayDetailsControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    private PayDetailsService payDetailsService;
    
    
}
