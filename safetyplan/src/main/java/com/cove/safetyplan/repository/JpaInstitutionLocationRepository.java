package com.cove.safetyplan.repository;

import com.cove.safetyplan.model.entities.InstitutionLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaInstitutionLocationRepository extends JpaRepository<InstitutionLocation, Long> {
}
