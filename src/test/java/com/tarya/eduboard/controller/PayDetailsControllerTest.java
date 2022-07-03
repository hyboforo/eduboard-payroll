/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarya.eduboard.dto.PayDetailsDto;
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
import com.tarya.eduboard.service.PayDetailsService;

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

    @Test
    public void getAllPayDetails() throws Exception {
        PayDetailsDto payDetailsDto = new PayDetailsDto(1, 3, "Baba", "Salifu", 2000, 25, 5, 10, 6, "Stanbic");
        List<PayDetailsDto> allPayDetails = Arrays.asList(payDetailsDto);

        Mockito.when(payDetailsService.getAllPayDetails()).thenReturn(allPayDetails);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/payDetails").accept(
                        MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{id:1,employeeId:3,employeeFirstName:Baba,employeeLastName:Salifu,grossSalary:2000,payeeRate:25,tierOneRate:5,tierTwoRate:10,tierThreeRate: 6,bank:Stanbic}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void addPayDetails() throws Exception {
        PayDetailsDto payDetailsDto = new PayDetailsDto(1, 3, "Baba", "Salifu", 2000, 25, 5, 10, 6, "Stanbic");

        Mockito.when(payDetailsService.createPayDetails(Mockito.anyLong(), Mockito.any(PayDetailsDto.class))).thenReturn(payDetailsDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/payDetails/savePayDetails/1")
                .accept(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsBytes(payDetailsDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{id:1,employeeId:3,employeeFirstName:Baba,employeeLastName:Salifu,grossSalary:2000,payeeRate:25,tierOneRate:5,tierTwoRate:10,tierThreeRate: 6,bank:Stanbic}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void updatePayDetails() throws Exception {
        PayDetailsDto payDetailsDto = new PayDetailsDto(1, 3, "Baba", "Salifu", 2000, 25, 5, 10, 6, "Stanbic");

        Mockito.when(payDetailsService.updateEmployeePayDetails(Mockito.anyLong(), Mockito.any(PayDetailsDto.class))).thenReturn(payDetailsDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/payDetails/updateEmployeePayDetails/1")
                .accept(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsBytes(payDetailsDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{id:1,employeeId:3,employeeFirstName:Baba,employeeLastName:Salifu,grossSalary:2000,payeeRate:25,tierOneRate:5,tierTwoRate:10,tierThreeRate:6,bank:Stanbic}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void getEmployee() throws Exception {
        PayDetailsDto payDetailsDto = new PayDetailsDto(1, 3, "Baba", "Salifu", 2000, 25, 5, 10, 6, "Stanbic");

        Mockito.when(payDetailsService.getPayDetailsByEmployeeId(Mockito.anyLong())).thenReturn(payDetailsDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/payDetails/returnEmployeePayDetails/1").accept(
                        MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{id:1,employeeId:3,employeeFirstName:Baba,employeeLastName:Salifu,grossSalary:2000,payeeRate:25,tierOneRate:5,tierTwoRate:10,tierThreeRate:6,bank:Stanbic}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}
