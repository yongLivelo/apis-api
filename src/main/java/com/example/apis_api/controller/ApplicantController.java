package com.example.apis_api.controller;

import com.example.apis_api.model.Applicant;
import com.example.apis_api.repo.ApplicantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ApplicantController {

    @Autowired
    private ApplicantRepo repo;

    @PostMapping("/addApplicant")
    public void addApplicant(@RequestBody Applicant applicant) {
        repo.save(applicant);
    }

    @GetMapping("/getApplicants")
    public List<Applicant> getApplicants() {
        return repo.findAll();
    }

    @DeleteMapping("/deleteApplicant/{id}")
    public void deleteApplicant(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
