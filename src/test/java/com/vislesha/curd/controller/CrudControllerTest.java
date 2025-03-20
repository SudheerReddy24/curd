package com.vislesha.curd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vislesha.curd.dto.CrudDto;
import com.vislesha.curd.entity.Crud;
import com.vislesha.curd.service.CrudServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CrudController.class)
class CrudControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CrudServiceImpl service;
    private Crud crudOne;
    private Crud crudTwo;
    private CrudDto crudDto;
    private List<Crud> crudList;

    @BeforeEach
    void setUp() {
        crudOne = new Crud(1,"bharath","bharath123",
                9898989898l, "male","ponnuru");
        crudTwo = new Crud(2,"akhil","akhil123",
                9898981122l, "male","guntur");
        crudList = Arrays.asList(crudOne,crudTwo);
        crudDto = new CrudDto(1,"bharath","bharath123",
                9898989898l, "male","ponnuru");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCreateData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(crudOne);

        when(service.addData(crudDto)).thenReturn("Data inserted Successfully");
        this.mockMvc.perform(post("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    void testGetDataById() throws Exception {
        when(service.getDataById(1)).thenReturn(crudOne);
        this.mockMvc.perform(get("/getDataById/1"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testRetrieveAllData() throws Exception {
        when(service.getAllData()).thenReturn(crudList);
        this.mockMvc.perform(get("/retrieve"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testDeleteData() throws Exception {
        when(service.deleteData(2)).thenReturn("Deleted successfully");
        this.mockMvc.perform(delete("/delete/2"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testUpdateData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(crudOne);

        when(service.update(crudDto)).thenReturn("Data updated successfully");
        this.mockMvc.perform(put("/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isCreated());
    }
}