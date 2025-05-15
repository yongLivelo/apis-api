package com.example.apis_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "applicant")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int age;
    private LocalDate dateOfBirth;
    private String sex;
    private String desiredPosition;
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate applicationDate;
    private String applicationStatus;
    private String trainingStatus;
}
