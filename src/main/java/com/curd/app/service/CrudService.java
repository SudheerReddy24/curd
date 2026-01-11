package com.curd.app.service;

import com.curd.app.dto.CrudDto;

import java.util.List;

public interface CrudService {

    String addData(CrudDto crudDto);

    List<CrudDto> getAllData();

    CrudDto getDataById(int id);

    String deleteDataById(int id);

    String update(int id, CrudDto crudDto);

}
