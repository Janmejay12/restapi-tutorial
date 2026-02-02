package com.example.RestAPI_practice.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private String employeeName;
    private Date joiningDate;
    private String department;
    private Date birthDate;
}
