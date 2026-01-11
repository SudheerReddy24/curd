package com.curd.app.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrudDto {

    @NotNull(message = "Id must not be empty")
    private int id;

    @NotBlank(message = "Username must not be empty")
    private String userName;

    @NotBlank(message = "Password must not be empty")
    private String password;

    @NotNull(message = "Mobile number must not be empty")
    @Min(value = 1000000000L, message = "Mobile number must be 10 digits")
    @Max(value = 9999999999L, message = "Mobile number must be 10 digits")
    private long mobileNumber;

    @NotBlank(message = "Gender must not be empty")
    private String gender;

    @NotBlank(message = "City must not be empty")
    private String city;
}
