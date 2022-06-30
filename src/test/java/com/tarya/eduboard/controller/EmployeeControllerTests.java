/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarya.eduboard.dto.EmployeeDto;
import com.tarya.eduboard.service.EmployeeService;
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
@WebMvcTest(value = EmployeeController.class)
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    private EmployeeService employeeService;

    @Test
    public void getAllEmployees() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto(2, "Hanan", "Yaro", "yaro@gmail.com", "Admin");
        List<EmployeeDto> allEmployees = Arrays.asList(employeeDto);

        Mockito.when(employeeService.getAllEmployees()).thenReturn(allEmployees);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/employee").accept(
                        MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"id\": 2,\"firstName\": \"Hanan\",\"lastName\": \"Yaro\",\"email\": \"yaro@gmail.com\",\"role\": \"Admin\"}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void addEmployee() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto(2, "Hanan", "Yaro", "yaro@gmail.com", "Admin");

        Mockito.when(employeeService.createEmployee(Mockito.any(EmployeeDto.class))).thenReturn(employeeDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/employee/saveEmployee")
                .accept(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsBytes(employeeDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        String expected = "{\"id\": 2,\"firstName\": \"Hanan\",\"lastName\": \"Yaro\",\"email\": \"yaro@gmail.com\",\"role\": \"Admin\"}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void updateEmployee() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto(2, "Hanan", "Yaro", "yaro@gmail.com", "Admin");

        Mockito.when(employeeService.updateEmployee(Mockito.anyLong(), Mockito.any(EmployeeDto.class))).thenReturn(employeeDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/employee/updateEmployee/2")
                .accept(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsBytes(employeeDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String expected = "{\"id\": 2,\"firstName\": \"Hanan\",\"lastName\": \"Yaro\",\"email\": \"yaro@gmail.com\",\"role\": \"Admin\"}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void failedDelete() throws Exception {
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/employee/deleteEmployee/2").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());

    }

    @Test
    public void getEmployee() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto(2, "Hanan", "Yaro", "yaro@gmail.com", "Admin");

        Mockito.when(employeeService.getEmployeeById(Mockito.anyLong())).thenReturn(employeeDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/employee/returnEmployee/2").accept(
                        MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"id\": 2,\"firstName\": \"Hanan\",\"lastName\": \"Yaro\",\"email\": \"yaro@gmail.com\",\"role\": \"Admin\"}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

}
