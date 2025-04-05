package com.example.apis_api.controller;

import com.example.apis_api.model.Applicant;
import com.example.apis_api.repo.ApplicantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class ApplicantController {

    @Autowired
    private ApplicantRepo repo;

    // Create
    @PostMapping("/addApplicant")
    public ResponseEntity<Applicant> addApplicant(@RequestBody Applicant applicant) {
        if (applicant.getId() != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (applicant.getApplicationDate() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Applicant savedApplicant = repo.save(applicant);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedApplicant);
    }

    // Get first applicant (to keep return type Applicant)
    @GetMapping("/getApplicants")
    public ResponseEntity<List<Applicant>> getFirstApplicant() {
        List<Applicant> applicants = repo.findAll();
        if (applicants.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(applicants); // Only returns first for compliance
    }

    // Delete (return deleted applicant instead of void)
    @DeleteMapping("/deleteApplicant/{id}")
    public ResponseEntity<Applicant> deleteApplicant(@PathVariable Long id) {
        Optional<Applicant> optionalApplicant = repo.findById(id);
        if (optionalApplicant.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Applicant deleted = optionalApplicant.get();
        repo.deleteById(id);
        return ResponseEntity.ok(deleted);
    }

    // Update (Partial)
    @PatchMapping("/updateApplicant/{id}")
    public ResponseEntity<Applicant> updateApplicant(@PathVariable Long id, @RequestBody Applicant newData) {
        Optional<Applicant> optionalApplicant = repo.findById(id);
        if (optionalApplicant.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Applicant existing = optionalApplicant.get();

        if (newData.getId() != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (newData.getApplicationDate() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (newData.getAge() != 0) existing.setAge(newData.getAge());
        if (newData.getDateOfBirth() != null) existing.setDateOfBirth(newData.getDateOfBirth());
        if (newData.getDesiredPosition() != null) existing.setDesiredPosition(newData.getDesiredPosition());
        if (newData.getLastName() != null) existing.setLastName(newData.getLastName());
        if (newData.getFirstName() != null) existing.setFirstName(newData.getFirstName());
        if (newData.getMiddleName() != null) existing.setMiddleName(newData.getMiddleName());
        if (newData.getApplicationStatus() != null) existing.setApplicationStatus(newData.getApplicationStatus());
        if (newData.getTrainingStatus() != null) existing.setTrainingStatus(newData.getTrainingStatus());

        Applicant updated = repo.save(existing);
        return ResponseEntity.ok(updated);
    }
}
