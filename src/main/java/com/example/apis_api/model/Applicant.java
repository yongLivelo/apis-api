package com.example.apis_api.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "applicant")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private int age;
    private LocalDate dateOfBirth;
    private String desiredPosition;
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate applicationDate;
    private String applicationStatus;
    private String trainingStatus;
}
