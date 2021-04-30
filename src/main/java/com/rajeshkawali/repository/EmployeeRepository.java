package com.rajeshkawali.repository;

import com.rajeshkawali.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee,String> {

    @Query("{'name': ?0}")
    Optional<Employee> findByName(String name);

}
