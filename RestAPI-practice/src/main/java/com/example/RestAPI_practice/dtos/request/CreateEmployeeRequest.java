package com.example.RestAPI_practice.dtos.request;

import com.example.RestAPI_practice.groups.OnCreate;
import com.example.RestAPI_practice.groups.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CreateEmployeeRequest {

    @NotNull(message = "Employee Name can not be null",groups = OnUpdate.class)
    @Size(min = 3, max = 50)
    private String employeeName;

    @NotNull(message = "Joining Date can not be null", groups = {OnUpdate.class, OnCreate.class})
    private Date joiningDate;

    private String department;

    @Past
    @NotNull(message = "Birth Date can not be null", groups = OnCreate.class)
    private Date birthDate;
}
