package com.example.demo.controller;

import com.example.demo.model.Applicants;
import com.example.demo.repository.ApplicantsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applicants")
public class ApplicantsController {

    private final ApplicantsRepository applicantsRepository;

    public ApplicantsController(ApplicantsRepository applicantsRepository) {
        this.applicantsRepository = applicantsRepository;
    }

    @GetMapping("/getAllApplicants")
    public List<Applicants> getAllApplicants() {
        return applicantsRepository.findAll();
    }

    @GetMapping("/getApplicant/{id}")
    public Applicants getApplicantById(@PathVariable Long id) {
        return applicantsRepository.findById(id).orElse(null);
    }

    @PostMapping("/createApplicant")
    public Applicants createApplicant(@RequestBody Applicants applicant) {
        return applicantsRepository.save(applicant);
    }

    @PutMapping("/updateApplicant/{id}")
    public Applicants updateApplicantById(@PathVariable Long id, @RequestBody Applicants updatedApplicant) {
        return applicantsRepository.findById(id).map(applicant -> {
            applicant.setName(updatedApplicant.getName());
            applicant.setEmail(updatedApplicant.getEmail());
            return applicantsRepository.save(applicant);
        }).orElse(null);
    }

    @DeleteMapping("/deleteApplicant/{id}")
    public String deleteApplicant(@PathVariable Long id) {
        applicantsRepository.deleteById(id);
        return "Applicant with ID " + id + " deleted.";
    }
}
