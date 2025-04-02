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

    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthDate;
    private String religion;
    private int age;
    private String city;
    private String province;
    private String civilStatus;
    private int height;
    private String trainingStatus;
    private String desiredPosition;
    private Boolean highSchoolGraduate;
    private Boolean collegeGraduate;

    @Column(nullable = false, updatable = false)
    private LocalDate applicationDate;

    @PrePersist
    protected void onCreate() {
        this.applicationDate = LocalDate.now(); // Set the date on entity creation
    }
}
