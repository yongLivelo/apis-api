package com.example.demo.repository;

import com.example.demo.model.Applicants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantsRepository extends JpaRepository<Applicants, Long> {
}
