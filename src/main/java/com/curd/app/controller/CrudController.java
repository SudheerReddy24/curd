package com.curd.app.controller;

import com.curd.app.dto.CrudDto;
import com.curd.app.service.CrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@Tag(name = "CRUD", description = "The CRUD API")
@RequiredArgsConstructor
public class CrudController {

    private final CrudService service;

    @PostMapping("/create")
    @Operation(summary = "Add a new data",
            description = "Provide details to add a new data")
    public ResponseEntity<String> createData(@Valid @RequestBody CrudDto crudDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addData(crudDto));
    }

    @GetMapping("/getDataById/{id}")
    @Operation(summary = "Get data by ID",
            description = "Provide an valid ID to get the specific data")
    public ResponseEntity<CrudDto> getDataById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getDataById(id));
    }

    @GetMapping("/retrieve")
    @Operation(summary = "Get all data")
    public ResponseEntity<List<CrudDto>> retrieveAllData() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllData());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete data by ID",
            description = "Provide an valid ID to delete the corresponding data")
    public ResponseEntity<String> deleteData(@PathVariable int id) {
        service.deleteDataById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping("/update")
    @Operation(summary = "Update data by ID",
            description = "Provide a valid ID to update a data")
    public ResponseEntity<String> updateData(@Valid @RequestBody CrudDto crudDto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(crudDto));
    }
}
