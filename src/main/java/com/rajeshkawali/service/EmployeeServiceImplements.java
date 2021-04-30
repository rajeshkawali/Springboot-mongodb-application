package com.rajeshkawali.service;

import com.rajeshkawali.entity.Employee;
import com.rajeshkawali.exception.EmployeeCollectionException;
import com.rajeshkawali.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplements implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void createEmployee(Employee employee) throws EmployeeCollectionException {
        Optional<Employee> nameOptional= employeeRepository.findByName(employee.getName());
        if(nameOptional.isPresent()){
            throw new EmployeeCollectionException(EmployeeCollectionException.EmployeeAlreadyExists());
        }
        else{
            employee.setCreatedAt(new Date(System.currentTimeMillis()));
            employeeRepository.save(employee);
        }
    }

    @Override
    public void updateEmployee(String id, Employee employee) throws EmployeeCollectionException {
        Optional<Employee> employeeWithId = employeeRepository.findById(id);
        Optional<Employee> employeeWithSameName = employeeRepository.findByName(employee.getName());
        if(employeeWithSameName.isPresent()){
            if(employeeWithSameName.isPresent() && !employeeWithSameName.get().getId().equals(id)){
                throw new EmployeeCollectionException(EmployeeCollectionException.EmployeeAlreadyExists());
            }
            Employee employeeToUpdate=employeeWithId.get();

            employeeToUpdate.setName(employee.getName());
            employeeToUpdate.setDescription(employee.getDescription());
            employeeToUpdate.setCompleted(employee.getCompleted());
            employeeToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
            employeeRepository.save(employeeToUpdate);
        } else{
            throw new EmployeeCollectionException(EmployeeCollectionException.NotFoundException(id));
        }
    }

    @Override
    public Employee getSingleEmployee(String id) throws EmployeeCollectionException {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (!employeeOptional.isPresent()) {
            throw new EmployeeCollectionException(EmployeeCollectionException.NotFoundException(id));
        }else {
            return employeeOptional.get();
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.size() > 0) {
            return employees;
        }else {
            return new ArrayList<Employee>();
        }
    }

    @Override
    public void deleteEmployeeById(String id) throws EmployeeCollectionException {
        Optional<Employee> todoOptional = employeeRepository.findById(id);
        if(!todoOptional.isPresent()){
            throw new EmployeeCollectionException(EmployeeCollectionException.NotFoundException(id));
        }else{
            employeeRepository.deleteById(id);
        }
    }
}
