package com.curd.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrudResponse {

    private Integer id;

    private String userName;

    private String password;

    private String phoneNumber;

    private String gender;

    private String city;


}
