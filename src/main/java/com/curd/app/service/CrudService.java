package com.curd.app.service;

import com.curd.app.dto.CrudDto;
import com.curd.app.entity.Crud;

import java.util.List;

public interface CrudService {

    public abstract String addData(CrudDto crudDto);

    public abstract List<Crud> getAllData();

    public abstract Crud getDataById(int id);

    public abstract String deleteData(int id);

    public abstract String update(CrudDto crudDto);

}
