package com.vislesha.curd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrudDto {

    private int id;
    private String userName;
    private String password;
    private long mobileNumber;
    private String gender;
    private String city;
}
