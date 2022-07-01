/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarya.eduboard.dto.PayDetailsDto;
import com.tarya.eduboard.dto.PaySlipDto;
import com.tarya.eduboard.service.PaySlipService;
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
@WebMvcTest(value = PaySlipController.class)
public class PaySlipControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    private PaySlipService paySlipService;

    @Test
    public void getAllPaySlips() throws Exception {
        PaySlipDto paySlipDto = new PaySlipDto(1, "2022-06-30", 100, 190, 273, 359, 922, 1077, 3);
        List<PaySlipDto> allPaySlips = Arrays.asList(paySlipDto);

        Mockito.when(paySlipService.getAllPaySlips()).thenReturn(allPaySlips);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/paySlip").accept(
                        MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{id:1,date:2022-06-30,tireOneContribution:100,tierTwoContribution:190,tierThreeContribution:273,payeeContributions:359,totalDeductions:922,netSalary:1077,employeeId:3}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void payEmployee() throws Exception {
        PaySlipDto paySlipDto = new PaySlipDto(1, "2022-06-30", 100, 190, 273, 359, 922, 1077, 3);

        Mockito.when(paySlipService.createPaySlip(Mockito.anyLong())).thenReturn(paySlipDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/paySlip/payEmployee/1")
                .accept(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsBytes(paySlipDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{id:1,date:2022-06-30,tireOneContribution:100,tierTwoContribution:190,tierThreeContribution:273,payeeContributions:359,totalDeductions:922,netSalary:1077,employeeId:3}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void payAllEmployee() throws Exception {
        PaySlipDto paySlipDto = new PaySlipDto(1, "2022-06-30", 100, 190, 273, 359, 922, 1077, 3);
        List<PaySlipDto> allPaySlips = Arrays.asList(paySlipDto);
        Mockito.when(paySlipService.createPaySlips()).thenReturn(allPaySlips);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/paySlip/payAllEmployees")
                .accept(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsBytes(paySlipDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{id:1,date:2022-06-30,tireOneContribution:100,tierTwoContribution:190,tierThreeContribution:273,payeeContributions:359,totalDeductions:922,netSalary:1077,employeeId:3}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void getEmployeePaySlip() throws Exception {
        PaySlipDto paySlipDto = new PaySlipDto(1, "2022-06-30", 100, 190, 273, 359, 922, 1077, 3);

        Mockito.when(paySlipService.getPaySlipByEmployeeId(Mockito.anyLong())).thenReturn(paySlipDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/paySlip/returnEmployeePaySlip/1").accept(
                        MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{id:1,date:2022-06-30,tireOneContribution:100,tierTwoContribution:190,tierThreeContribution:273,payeeContributions:359,totalDeductions:922,netSalary:1077,employeeId:3}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}
