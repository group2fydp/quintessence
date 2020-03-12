package com.cove.safetyplan.repository;

import com.cove.safetyplan.model.entities.InstitutionLocation;
import com.cove.safetyplan.model.entities.MentalHealthService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaMentalHealthServiceRepository extends JpaRepository<MentalHealthService, Long> {
    List<MentalHealthService> findAllByInstitutionLocation(InstitutionLocation institutionLocation);
}
