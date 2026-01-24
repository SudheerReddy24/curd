package com.curd.app.controller;

import com.curd.app.dto.CrudResponse;
import com.curd.app.service.CrudService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.curd.app.dto.CrudRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
    private CrudService service;
    private CrudRequest crudRequest;
    private CrudResponse crudResponse;
    private CrudResponse crudResponseTwo;
    private List<CrudResponse> crudList;

    @BeforeEach
    void setUp() {
        crudRequest = new CrudRequest(1, "bharath","bharath123",
                9898989898L, "male","ponnuru");
        crudResponse = new CrudResponse(1,"bharath","bharath123",
                9898989898L, "male","ponnuru");
        crudResponse = new CrudResponse(2, "akhil", "akhil123",
                9898981122L, "male", "guntur");
        crudList = Arrays.asList(crudResponse, crudResponseTwo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCreateData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(crudResponse);

        when(service.addData(crudRequest)).thenReturn("Data inserted Successfully");
        this.mockMvc.perform(post("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    void testGetDataById() throws Exception {
        when(service.getDataById(1)).thenReturn(crudResponse);
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
        when(service.deleteDataById(2)).thenReturn("Deleted successfully");
        this.mockMvc.perform(delete("/delete/2"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testUpdateData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(crudRequest);

        when(service.update(1, crudRequest)).thenReturn("Data updated successfully");
        this.mockMvc.perform(put("/update/{id}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isCreated());
    }
}