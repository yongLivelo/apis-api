package com.example.demo.controller;

import com.example.demo.model.Applicants;
import com.example.demo.repository.ApplicantsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applicants")
public class ApplicantsController {

    private final ApplicantsRepository repository;

    public ApplicantsController(ApplicantsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getApplicants")
    public List<Applicants> getAllApplicants() {
        return repository.findAll();
    }

    @GetMapping("/getApplicant/{id}")
    public Applicants getApplicantById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/addApplicant")
    public Applicants createApplicant(@RequestBody Applicants applicant) {
        return repository.save(applicant);
    }

    @PutMapping("/updateApplicant/{id}")
    public Applicants updateApplicant(@PathVariable Long id, @RequestBody Applicants updatedApplicant) {
        return repository.findById(id).map(applicant -> {
            applicant.setName(updatedApplicant.getName());
            applicant.setEmail(updatedApplicant.getEmail());
            return repository.save(applicant);
        }).orElse(null);
    }

    @DeleteMapping("/deleteApplicant/{id}")
    public String deleteApplicant(@PathVariable Long id) {
        repository.deleteById(id);
        return "Applicant with ID " + id + " deleted.";
    }
}
