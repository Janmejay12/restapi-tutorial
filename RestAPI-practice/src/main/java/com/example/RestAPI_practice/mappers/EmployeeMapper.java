package com.example.RestAPI_practice.mappers;

import com.example.RestAPI_practice.dtos.request.CreateEmployeeRequest;
import com.example.RestAPI_practice.dtos.response.EmployeeResponse;
import com.example.RestAPI_practice.entities.Employee;

public class EmployeeMapper {
    public static Employee toEntity(CreateEmployeeRequest request){
        Employee employee = new Employee();
        employee.setBirthDate(request.getBirthDate());
        employee.setEmployeeName(request.getEmployeeName());
        employee.setDepartment(request.getDepartment());
        employee.setJoiningDate(request.getJoiningDate());
        return employee;
    }

    public static EmployeeResponse toDto(Employee employee){
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeName(employee.getEmployeeName());
        employeeResponse.setBirthDate(employee.getBirthDate());
        employeeResponse.setDepartment(employee.getDepartment());
        employeeResponse.setJoiningDate(employee.getJoiningDate());
        return employeeResponse;
    }
}
