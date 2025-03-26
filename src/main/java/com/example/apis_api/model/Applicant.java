package com.example.apis_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="applicant")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
}
