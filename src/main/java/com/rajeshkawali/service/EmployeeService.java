package com.rajeshkawali.service;

import com.rajeshkawali.entity.Employee;
import com.rajeshkawali.exception.EmployeeCollectionException;

import java.util.List;

public interface EmployeeService {

    void createEmployee(Employee employee) throws EmployeeCollectionException;
    void updateEmployee(String id, Employee employee) throws EmployeeCollectionException;
    void deleteEmployeeById(String id) throws EmployeeCollectionException;
    Employee getSingleEmployee(String id) throws EmployeeCollectionException;
    List<Employee> getAllEmployees();
}
