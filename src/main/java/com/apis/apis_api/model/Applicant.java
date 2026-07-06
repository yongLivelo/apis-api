package com.apis.apis_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "applicants")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String applicationId;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false)
    private String desiredPosition;

    @Column(nullable = false)
    private LocalDate applicationDate;

    @Column(nullable = false)
    private String applicationStatus;

    @Column(nullable = false)
    private String trainingStatus;

    private String image;

}
