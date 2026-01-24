package com.curd.app.service;

import com.curd.app.dto.CrudRequest;
import com.curd.app.dto.CrudResponse;
import com.curd.app.entity.Crud;
import com.curd.app.exception.ResourceNotFoundException;
import com.curd.app.repository.CrudRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CrudServiceImpl implements CrudService {

    private final CrudRepository repository;

    //Add Data
    @Override
    public String addData(CrudRequest crudRequest) {

        Crud crud = toEntity(crudRequest);

        repository.save(crud);

        log.info("Data inserted Successfully {}", crudRequest);
        return "Data inserted successfully";
    }

    //Get all data
    @Override
    public List<CrudResponse> getAllData() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    //Get data by id
    @Override
    public CrudResponse getDataById(Integer id) {
        Crud crud = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No data found for ID: " + id));
        return toDto(crud);
    }

    //Delete data by ID
    @Override
    public String deleteDataById(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No data found with ID: " + id);
        }
        repository.deleteById(id);
        return "Data with ID: " + id + " deleted successfully";
    }

    //Update data
    @Override
    public String update(Integer id, CrudRequest crudRequest) {
        Crud existing = repository.findById(crudRequest.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("No data found with ID: " + crudRequest.getId()));

        existing.setUserName(crudRequest.getUserName());
        existing.setPassword(crudRequest.getPassword());
        existing.setMobileNumber(crudRequest.getMobileNumber());
        existing.setGender(crudRequest.getGender());
        existing.setCity(crudRequest.getCity());

        repository.save(existing);
        log.info("Data updated successfully {}", existing);
        return "Data updated successfully";
    }

    //Map Entity to Dto
    private Crud toEntity(CrudRequest crudRequest) {
        Crud crud = new Crud();

        crud.setUserName(crudRequest.getUserName());
        crud.setPassword(crudRequest.getPassword());
        crud.setMobileNumber(crudRequest.getMobileNumber());
        crud.setGender(crudRequest.getGender());
        crud.setCity(crudRequest.getCity());

        return crud;
    }

    //Map Dto to Entity
    private CrudResponse toDto(Crud entity) {

        return new CrudResponse(
                entity.getId(),
                entity.getUserName(),
                entity.getPassword(),
                entity.getMobileNumber(),
                entity.getGender(),
                entity.getCity()
        );
    }

}
