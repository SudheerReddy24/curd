package com.vislesha.curd.repository;

import com.vislesha.curd.entity.Crud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudRepository extends JpaRepository<Crud,Integer> {

}
