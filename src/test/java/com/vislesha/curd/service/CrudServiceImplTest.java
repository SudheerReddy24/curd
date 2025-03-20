package com.vislesha.curd.service;

import com.vislesha.curd.CrudApplication;
import com.vislesha.curd.dto.CrudDto;
import com.vislesha.curd.entity.Crud;
import com.vislesha.curd.repository.CrudRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CrudApplication.class)
class CrudServiceImplTest {

    @Mock
    private CrudRepository repository;

    @InjectMocks
    private CrudServiceImpl service;

    private Crud crud;
    private Crud crudTwo;
    private CrudDto crudDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        crud = new Crud(1, "sai@gmail.com", "sai1234",
                9022225678L, "male", "guntur");
        crudTwo = new Crud(2, "bharath@gmail.com", "bharath1234",
                9011115678L, "male", "ponnuru");
        crudDto = new CrudDto(1, "sai@gmail.com", "sai1234",
                9022225678L, "male", "guntur");
    }

    @Test
    void testAddData() {
        when(repository.save(any(Crud.class))).thenReturn(crud);

        String result = service.addData(crudDto);

        assertEquals("Data inserted Successfully", result);
        verify(repository, times(1)).save(any(Crud.class));
    }

    @Test
    void testGetDataById() {
        when(repository.findById(1)).thenReturn(Optional.of(crud));

        Crud result = service.getDataById(1);

        assertEquals(crud, result);
        verify(repository, times(1)).findById(1);
    }

    @Test
    void testGetAllData() {
        when(repository.findAll()).thenReturn(Arrays.asList(crud, crudTwo));

        List<Crud> result = service.getAllData();

        assertEquals(2, result.size());
        assertEquals(crud, result.get(0));
        assertEquals(crudTwo, result.get(1));
        verify(repository, times(1)).findAll();
    }

    @Test
    void testDeleteData() {
        when(repository.findById(1)).thenReturn(Optional.of(crud));
        doNothing().when(repository).deleteById(1);

        String result = service.deleteData(1);

        assertEquals("Deleted successfully", result);
        verify(repository, times(1)).findById(1);
        verify(repository, times(1)).deleteById(1);
    }

    @Test
    void testUpdate() {
        when(repository.save(any(Crud.class))).thenReturn(crud);

        String result = service.update(crudDto);

        assertEquals("Data updated successfully", result);
        verify(repository, times(1)).save(any(Crud.class));
    }
}

//package com.vislesha.curd.service;
//
//import com.github.tomakehurst.wiremock.WireMockServer;
//import com.vislesha.curd.CrudApplication;
//import com.vislesha.curd.dto.CrudDto;
//import com.vislesha.curd.entity.Crud;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.*;
//
//import static com.github.tomakehurst.wiremock.client.WireMock.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(classes = CrudApplication.class)
//class CrudServiceImplTest {
//
//    private WireMockServer wireMockServer;
//    @Autowired
//    private CrudServiceImpl service;
//    private Crud crud;
//    private Crud crudTwo;
//    private CrudDto crudDto;
//
//    @BeforeEach
//    void setUp() {
//        crud = new Crud(1, "sai@gmail.com", "sai1234",
//                9022225678L, "male", "guntur");
//        crudTwo = new Crud(2,"bharath@gmail.com","bharath1234",
//                9011115678L,"male","ponnuru");
//        crudDto = new CrudDto(1,"sai@gmail.com","sai1234",
//                9022225678L,"male","guntur");
//        wireMockServer = new WireMockServer(8080);
//        wireMockServer.start();
//        configureFor("localhost", 8080);
//    }
//
//    @AfterEach
//    void tearDown() {
//        wireMockServer.stop();
//    }
//
//    @Test
//    void testAddData() {
//        stubFor(patch(urlEqualTo("/create"))
//                .willReturn(aResponse()
//                        .withStatus(201)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody("Data inserted Successfully")));
//        String result = service.addData(crudDto);
//        assertEquals("Data inserted Successfully",result);
//
////        verify(postRequestedFor(urlEqualTo("/create"))
////                .withRequestBody(containing("sai@gmail.com")));
//    }
//
//    @Test
//    void testGetDataById() {
//        stubFor(get(urlEqualTo("/getDataById/1"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody("{ \"id\":1, \"userName\":\"sai@gmail.com\",\"password\":\"sai1234\"," +
//                                "\"mobileNumber\":9022225678L,\"gender\":\"male\"," +
//                                "\"city\":\"guntur\"}")));
//        Crud result = service.getDataById(1);
//
//        assertEquals(1, result.getId());
//        assertEquals("sai@gmail.com", result.getUserName());
//        assertEquals("sai1234", result.getPassword());
//        assertEquals(9022225678L, result.getMobileNumber());
//        assertEquals("male", result.getGender());
//        assertEquals("guntur", result.getCity());
//
////        verify(getRequestedFor(urlEqualTo("/getDataById/1")));
//    }
//
//    @Test
//    void getAllData() {
//        stubFor(get(urlEqualTo("/retrieve"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody("true")));
//        List<Crud> result = service.getAllData();
//
//        assertEquals(crud,result.get(0));
//        assertEquals(crudTwo,result.get(1));
//    }
//
//    @Test
//    void deleteData() {
//        stubFor(delete(urlEqualTo("/delete/1"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody("Deleted successfully")));
//        String result = service.deleteData(1);
//
//        assertEquals("Deleted successfully",result);
//    }
//
//    @Test
//    void update() {
//        stubFor(put(urlEqualTo("/update"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody("Data updated successfully")));
//        String result = service.update(crudDto);
//
//        assertEquals("Data updated successfully",result);
//    }
//}