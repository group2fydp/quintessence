package com.cove.safetyplan.repository;

import com.cove.safetyplan.model.entities.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaInstitutionRepository extends JpaRepository<Institution, Long> {
}
