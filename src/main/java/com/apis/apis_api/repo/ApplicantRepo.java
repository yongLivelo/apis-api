package com.apis.apis_api.repo;

import com.apis.apis_api.model.Applicant;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

@Repository
public interface ApplicantRepo extends JpaRepository<Applicant, Long>, JpaSpecificationExecutor<Applicant> {

    @Query(value = "SELECT COALESCE(MAX(CAST(SUBSTRING(application_id, 6) AS UNSIGNED)), 0) FROM applicants WHERE application_id LIKE CONCAT(YEAR(CURDATE()), '-%')", nativeQuery = true)
    Long getMaxSequenceForCurrentYear();

    static Specification<Applicant> filterBy(Applicant filters) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.getApplicationId() != null)
                predicates.add(cb.like(cb.lower(root.get("applicationId")),
                        "%" + filters.getApplicationId().toLowerCase() + "%"));
            if (filters.getFirstName() != null)
                predicates.add(cb.like(cb.lower(root.get("firstName")),
                        "%" + filters.getFirstName().toLowerCase() + "%"));
            if (filters.getLastName() != null)
                predicates.add(cb.like(cb.lower(root.get("lastName")),
                        "%" + filters.getLastName().toLowerCase() + "%"));
            if (filters.getMiddleName() != null)
                predicates.add(cb.like(cb.lower(root.get("middleName")),
                        "%" + filters.getMiddleName().toLowerCase() + "%"));
            if (filters.getSex() != null)
                predicates.add(cb.equal(root.get("sex"), filters.getSex()));
            if (filters.getDesiredPosition() != null)
                predicates.add(cb.like(cb.lower(root.get("desiredPosition")),
                        "%" + filters.getDesiredPosition().toLowerCase() + "%"));
            if (filters.getApplicationStatus() != null)
                predicates.add(cb.equal(root.get("applicationStatus"), filters.getApplicationStatus()));
            if (filters.getTrainingStatus() != null)
                predicates.add(cb.equal(root.get("trainingStatus"), filters.getTrainingStatus()));
            if (filters.getAge() != null)
                predicates.add(cb.equal(root.get("age"), filters.getAge()));
            if (filters.getBirthDate() != null)
                predicates.add(cb.equal(root.get("birthDate"), filters.getBirthDate()));
            if (filters.getApplicationDate() != null)
                predicates.add(cb.equal(root.get("applicationDate"), filters.getApplicationDate()));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
