package com.apis.apis_api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.apis.apis_api.model.Applicant;
import com.apis.apis_api.repo.ApplicantRepo;

@RestController

@RequestMapping("/api/applicants")
public class ApplicantController {
    @Autowired
    private ApplicantRepo applicantRepo;

    @GetMapping
    public List<Applicant> getALlApplicants() {

        return applicantRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<Applicant> createApplicant(@RequestBody Applicant newApplicant) {
        return ResponseEntity.ok(applicantRepo.save(newApplicant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable Long id) {
        return applicantRepo.findById(id).map(
                applicant -> {
                    applicantRepo.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                }

        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")

    public ResponseEntity<Applicant> updateApplicant(@PathVariable Long id, @RequestBody Applicant updatedApplicant) {
        return applicantRepo.findById(id)
                .map(applicant -> {
                    applicant.setFirstName(updatedApplicant.getFirstName());
                    applicant.setLastName(updatedApplicant.getLastName());
                    return ResponseEntity.ok(applicantRepo.save(applicant));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
