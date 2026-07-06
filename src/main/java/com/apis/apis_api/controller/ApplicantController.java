package com.apis.apis_api.controller;

import java.time.Year;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.apis.apis_api.model.Applicant;
import com.apis.apis_api.repo.ApplicantRepo;
import com.apis.apis_api.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/api/applicant")
public class ApplicantController {
    @Autowired
    private ApplicantRepo applicantRepo;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/search")
    public List<Applicant> searchApplicants(@RequestBody Applicant filters) {
        return applicantRepo.findAll(ApplicantRepo.filterBy(filters));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Applicant> getApplicantById(@PathVariable Long id) {
        return applicantRepo.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getNextId")
    public ResponseEntity<String> getNextApplicantId() {
        long seq = applicantRepo.getMaxSequenceForCurrentYear() + 1;
        String id = String.format("%s-%05d", Year.now(), seq);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Applicant> saveApplicant(@RequestBody Applicant applicant) {
        if (applicant.getId() == null) {
            long seq = applicantRepo.getMaxSequenceForCurrentYear() + 1;
            applicant.setApplicationId(String.format("%s-%05d", Year.now(), seq));
        }
        return ResponseEntity.ok(applicantRepo.save(applicant));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable Long id) {
        return applicantRepo.findById(id).map(
                applicant -> {
                    applicantRepo.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                }

        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<Applicant> uploadApplicantImage(@PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        return applicantRepo.findById(id)
                .map(applicant -> {
                    String filename = fileStorageService.store(file);
                    applicant.setImage(filename);
                    return ResponseEntity.ok(applicantRepo.save(applicant));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> downloadApplicantImage(@PathVariable Long id) {
        return applicantRepo.findById(id)
                .map(applicant -> {
                    Resource resource = fileStorageService.load(applicant.getImage());
                    String contentType = "application/octet-stream";
                    return ResponseEntity.ok()
                            .contentType(MediaType.parseMediaType(contentType))
                            .header(HttpHeaders.CONTENT_DISPOSITION,
                                    "inline; filename=\"" + resource.getFilename() + "\"")
                            .body(resource);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/image")
    public ResponseEntity<Void> deleteApplicantImage(@PathVariable Long id) {
        return applicantRepo.findById(id)
                .map(applicant -> {
                    if (applicant.getImage() != null) {
                        fileStorageService.delete(applicant.getImage());
                        applicant.setImage(null);
                        applicantRepo.save(applicant);
                    }
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
