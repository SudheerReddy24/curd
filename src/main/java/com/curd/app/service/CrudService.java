package com.curd.app.service;

import com.curd.app.dto.CrudRequest;
import com.curd.app.dto.CrudResponse;

import java.util.List;

public interface CrudService {

    String addData(CrudRequest crudRequest);

    List<CrudResponse> getAllData();

    CrudResponse getDataById(Integer id);

    String deleteDataById(Integer id);

    String update(Integer id, CrudRequest crudRequest);

}
