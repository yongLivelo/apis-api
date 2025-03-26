package com.example.apis_api.repo;

import com.example.apis_api.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ApplicantRepo extends JpaRepository<Applicant, Long> {

}
