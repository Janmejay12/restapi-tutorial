package com.example.RestAPI_practice.services;

import com.example.RestAPI_practice.dtos.request.CreateEmployeeRequest;
import com.example.RestAPI_practice.dtos.response.EmployeeResponse;
import com.example.RestAPI_practice.entities.Employee;
import com.example.RestAPI_practice.mappers.EmployeeMapper;
import com.example.RestAPI_practice.repos.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeResponse createEmployee(CreateEmployeeRequest request){
        Employee employee = EmployeeMapper.toEntity(request);
        return EmployeeMapper.toDto(employeeRepository.save(employee));
    }

    public List<EmployeeResponse> getAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public EmployeeResponse getEmployeeById(Long id){
        return EmployeeMapper.toDto(employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id :" + id)));
    }

    public Optional<EmployeeResponse> updateEmployee(Long id, CreateEmployeeRequest request){
        Employee employee = employeeRepository.findById(id).orElseThrow();
        if(employee == null){
         throw  new EntityNotFoundException("Employee not found with id :" + id);
        }
        else {
            Employee updateEmployee = EmployeeMapper.toEntity(request);
            updateEmployee.setEmployeeId(id);
            return Optional.of(EmployeeMapper.toDto(employeeRepository.save(updateEmployee)));
        }
    }

    public void deleteEmployee(Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow();
        if(employee == null){
            throw  new EntityNotFoundException("Employee not found with id :" + id);
        }
        else {
            employeeRepository.delete(employee);
        }

    }
}
