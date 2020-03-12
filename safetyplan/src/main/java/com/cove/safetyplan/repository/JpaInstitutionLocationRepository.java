package com.cove.safetyplan.repository;

import com.cove.safetyplan.model.entities.InstitutionLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaInstitutionLocationRepository extends JpaRepository<InstitutionLocation, Long> {
    Optional<InstitutionLocation> findByInstitutionLocationId(long institutionLocationId);
}
