package com.curd.app.service;

import com.curd.app.dto.CrudDto;
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
    public String addData(CrudDto crudDto) {

        Crud crud = toEntity(crudDto);

        repository.save(crud);

        log.info("Data inserted Successfully {}", crudDto);
        return "Data inserted successfully";
    }

    //Get all data
    @Override
    public List<CrudDto> getAllData() {

        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    //Get data by id
    @Override
    public CrudDto getDataById(int id) {

        Crud crud = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No data found for ID: " + id));
        return toDto(crud);
    }

    //Delete data by ID
    @Override
    public String deleteDataById(int id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No data found with ID: " + id);
        }

        repository.deleteById(id);

        return null;
    }

    //Update data
    @Override
    public String update(CrudDto crudDto) {
        Crud existing = repository.findById(crudDto.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("No data found with ID: " + crudDto.getId()));

        existing.setId(crudDto.getId());
        existing.setUserName(crudDto.getUserName());
        existing.setPassword(crudDto.getPassword());
        existing.setMobileNumber(crudDto.getMobileNumber());
        existing.setGender(crudDto.getGender());
        existing.setCity(crudDto.getCity());

        log.info("Data updated successfully {}", existing);
        return "Data updated successfully";
    }

    //Map Entity to Dto
    private Crud toEntity(CrudDto crudDto) {

        return new Crud(
                crudDto.getId(),
                crudDto.getUserName(),
                crudDto.getPassword(),
                crudDto.getMobileNumber(),
                crudDto.getGender(),
                crudDto.getCity()
        );
    }

    //Map Dto to Entity
    private CrudDto toDto(Crud entity) {

        return new CrudDto(
                entity.getId(),
                entity.getUserName(),
                entity.getPassword(),
                entity.getMobileNumber(),
                entity.getGender(),
                entity.getCity()
        );
    }

}
