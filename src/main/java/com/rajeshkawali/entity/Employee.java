package com.rajeshkawali.entity;


import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="employee")
public class Employee {

    @Id
    private String id;

    @NotNull(message="name cannot be null")
    private String name;

    @NotNull(message="Description cannot be null")
    private String description;

    @NotNull(message="Completed cannot be null")
    private Boolean completed;

    private Date createdAt;

    private Date updatedAt;
}
