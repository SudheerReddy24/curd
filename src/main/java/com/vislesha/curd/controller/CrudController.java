package com.vislesha.curd.controller;

import com.vislesha.curd.dto.CrudDto;
import com.vislesha.curd.entity.Crud;
import com.vislesha.curd.service.CrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@Tag(name = "CRUD", description = "The CRUD API")
public class CrudController {

    @Autowired
    private CrudService service;

    @PostMapping("/create")
    @Operation(summary = "Add a new data",
            description = "Provide necessary details to add a new data")
    public ResponseEntity<String> createData(@RequestBody CrudDto crudDto){
        try{
            String cd = service.addData(crudDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(cd);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getDataById/{id}")
    @Operation(summary = "Get data by ID",
            description = "Provide an valid ID to get the specific data")
    public ResponseEntity<Crud> getDataById(@PathVariable("id") int id){
        try{
            Crud crud = service.getDataById(id);
            if (crud != null){
                return ResponseEntity.status(HttpStatus.OK).body(crud);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/retrieve")
    @Operation(summary = "Get all data")
            public ResponseEntity<List<Crud>> retrieveAllData(){
        try{
            List<Crud> curd = service.getAllData();
            return ResponseEntity.status(HttpStatus.OK).body(curd);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete data by ID",
            description = "Provide an valid ID to delete the corresponding data")
    public ResponseEntity<String> deleteData(@PathVariable int id){
        try{
            String cd = service.deleteData(id);
            return ResponseEntity.status(HttpStatus.OK).body(cd);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update")
    @Operation(summary = "Update data by ID",
            description = "Provide a valid ID to update a data")
    public ResponseEntity<String> updateData(@RequestBody CrudDto crudDto){
        try {
            String cd = service.update(crudDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(cd);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
