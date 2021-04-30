package com.rajeshkawali.controller;

import com.rajeshkawali.entity.Employee;
import com.rajeshkawali.exception.EmployeeCollectionException;
import com.rajeshkawali.service.EmployeeService;
import com.rajeshkawali.configuration.UserProfileConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserProfileConfig userProfileConfig;

    @PostMapping("/save")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        try {
            employeeService.createEmployee(employee);
            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (EmployeeCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody Employee employee){
        try {
            employeeService.updateEmployee(id,employee);
            return new ResponseEntity<>("Updated employee details with id "+id+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (EmployeeCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) throws EmployeeCollectionException{
        try{
            employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>("Successfully deleted employee with id "+id, HttpStatus.OK);
        }catch (EmployeeCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> employee = employeeService.getAllEmployees();
        return new ResponseEntity<>(employee, employee.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getSingleEmployee(@PathVariable("id") String id){
        try {
            return new ResponseEntity<Employee>(employeeService.getSingleEmployee(id), HttpStatus.OK);
        } catch (EmployeeCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/config")
    public void fetchConfig(){
        System.out.println("Size of the config list:"+userProfileConfig.getIdentityTypes().size());
        boolean isIdentityTypeValid = false;
        for (String allowedIdentityType : userProfileConfig.getIdentityTypes()) {
            System.out.println(allowedIdentityType);
        }
    }
}
