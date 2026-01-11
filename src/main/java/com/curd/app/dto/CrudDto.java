package com.curd.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrudDto {

    @NotBlank(message = "Id must not be empty")
    private int id;

    @NotBlank(message = "Username must not be empty")
    private String userName;

    @NotBlank(message = "Password must not be empty")
    private String password;

    @NotBlank(message = "Mobile number must not be empty")
    private long mobileNumber;

    @NotBlank(message = "Gender must not be empty")
    private String gender;

    @NotBlank(message = "City must not be empty")
    private String city;
}
