package com.curd.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Crud implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    private int id;
    private String userName;
    private String password;
    private long mobileNumber;
    private String gender;
    private String city;

}
