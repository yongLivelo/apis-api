package com.apis.apis_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Applicant createApplicant(@RequestBody Applicant newApplicant) {
        return applicantRepo.save(newApplicant);
    }
}
