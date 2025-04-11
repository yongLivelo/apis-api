package com.example.apis_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "applicant")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate applicationDate;
    private String applicationStatus;
    private String lastName;
    private String firstName;
    private String middleName;
    private int age;
    private LocalDate dateOfBirth;
    private String desiredPosition;
    private String trainingStatus;

    @PrePersist
    protected void onCreate() {
        this.applicationDate = LocalDate.now();
    }
}
