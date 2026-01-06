package com.curd.app.service;

import com.curd.app.dto.CrudDto;
import com.curd.app.entity.Crud;
import com.curd.app.repository.CrudRepository;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CrudServiceImpl implements CrudService {

    @Autowired
    private CrudRepository repository;

    @Override
    public String addData(CrudDto crudDto) {
        try{
            Crud crud = new Crud();

            crud.setId(crudDto.getId());
            crud.setUserName(crudDto.getUserName());
            crud.setPassword(crudDto.getPassword());
            crud.setCity(crudDto.getCity());
            crud.setGender(crudDto.getGender());
            crud.setMobileNumber(crudDto.getMobileNumber());

            repository.save(crud);
            log.info("Data inserted Successfully {}",crudDto);
            return "Data inserted Successfully";
        }
        catch (Exception e){
            log.error("Error occurred while inserting data: {}",e.getMessage());
            throw new RuntimeException("Error occurred while inserting data");
        }
    }

    @Override
    public List<Crud> getAllData() {
        try{
            List<Crud> curd = repository.findAll();
            log.info("All data fetched successfully");
            return curd;
        }
        catch (Exception e){
            log.error("An error occurred while retrieving data: {}",e.getMessage());
            throw new RuntimeException("An error occurred while retrieving data");
        }
    }

    @Override
    public Crud getDataById(int id) {
        try{
            Optional<Crud> curd = repository.findById(id);
            if(curd.isPresent()){
                log.info("Data with ID {} fetched successfully",id);
                return curd.get();
            }
            else {
                log.warn("No data found for ID: {}",id);
                throw new RuntimeException("No data found for ID: "+id);
            }
        }
        catch (Exception e){
            log.error("An error occurred while fetching data by ID: {}{}",id,e.getMessage());
            throw new RuntimeException("An error occurred while fetching data by ID ",e);
        }
    }

    @Override
    public String deleteData(int id) {
        Optional<Crud> crud = repository.findById(id);
        try{
            if (crud.isPresent()){
                repository.deleteById(id);
                log.info("Data with ID {} deleted successfully", id);
                return "Deleted successfully";
            }
            else {
                log.warn("No data found for ID {} to delete",id);
                throw new  RuntimeException("No data found for ID: "+id+" to delete");
            }
        }
        catch (Exception e){
            log.error("Error occurred while deleting the data: {}",e.getMessage(),e);
            throw new RuntimeException("Error occurred while deleting the data");
        }
    }

    @Override
    public String update(CrudDto crudDto) {
        try {
            Crud crud = new Crud();

            crud.setId(crudDto.getId());
            crud.setUserName(crudDto.getUserName());
            crud.setPassword(crudDto.getPassword());
            crud.setCity(crudDto.getCity());
            crud.setGender(crudDto.getGender());
            crud.setMobileNumber(crudDto.getMobileNumber());

            repository.save(crud);
            log.info("Data updated successfully {}", crud);
            return "Data updated successfully";
        } catch (Exception e) {
            log.error("Error occurred while updating data: {}", e.getMessage(),e);
            return null;
        }
    }
}
