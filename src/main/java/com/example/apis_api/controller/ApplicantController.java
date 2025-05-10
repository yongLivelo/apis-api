package com.example.apis_api.controller;

import com.example.apis_api.model.Applicant;
import com.example.apis_api.repo.ApplicantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class ApplicantController {

    @Autowired
    private ApplicantRepo repo;


    @PostMapping("/addApplicant")
    public ResponseEntity<Applicant> addApplicant(@RequestBody Applicant applicant) {
        if (applicant.getId() != 0) return ResponseEntity.badRequest().build();

        Applicant savedApplicant = repo.save(applicant);
        return ResponseEntity.ok(savedApplicant);
    }


    @GetMapping("/getApplicants")
    public ResponseEntity<List<Applicant>> getApplicants() {
        List<Applicant> applicants = repo.findAll();
        if (applicants.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(applicants);
    }


    @DeleteMapping("/deleteApplicant/{id}")
    public ResponseEntity<Applicant> deleteApplicant(@PathVariable Long id) {
        Optional<Applicant> optionalApplicant = repo.findById(id);
        if (optionalApplicant.isEmpty()) return ResponseEntity.notFound().build();

        Applicant deleted = optionalApplicant.get();
        repo.deleteById(id);
        return ResponseEntity.ok(deleted);
    }


    @PatchMapping("/updateApplicant/{id}")
    public ResponseEntity<Applicant> updateApplicant(@PathVariable Long id, @RequestBody Applicant newData) {
        Optional<Applicant> optionalApplicant = repo.findById(id);
        if (optionalApplicant.isEmpty()) return ResponseEntity.notFound().build();

        Applicant existing = optionalApplicant.get();
        if (newData.getId() != 0) return ResponseEntity.badRequest().build();

        Applicant updated = existing.toBuilder()
                .age(newData.getAge() != 0 ? newData.getAge() : existing.getAge())
                .dateOfBirth(newData.getDateOfBirth() != null ? newData.getDateOfBirth() : existing.getDateOfBirth())
                .desiredPosition(newData.getDesiredPosition() != null ? newData.getDesiredPosition() : existing.getDesiredPosition())
                .lastName(newData.getLastName() != null ? newData.getLastName() : existing.getLastName())
                .firstName(newData.getFirstName() != null ? newData.getFirstName() : existing.getFirstName())
                .middleName(newData.getMiddleName() != null ? newData.getMiddleName() : existing.getMiddleName())
                .applicationStatus(newData.getApplicationStatus() != null ? newData.getApplicationStatus() : existing.getApplicationStatus())
                .trainingStatus(newData.getTrainingStatus() != null ? newData.getTrainingStatus() : existing.getTrainingStatus())
                .build();

        return ResponseEntity.ok(repo.save(updated));

    }
}
