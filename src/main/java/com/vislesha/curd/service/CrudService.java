package com.vislesha.curd.service;

import com.vislesha.curd.dto.CrudDto;
import com.vislesha.curd.entity.Crud;

import java.util.List;

public interface CrudService {

    public abstract String addData(CrudDto crudDto);

    public abstract List<Crud> getAllData();

    public abstract Crud getDataById(int id);

    public abstract String deleteData(int id);

    public abstract String update(CrudDto crudDto);

}
