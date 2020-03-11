package com.cove.safetyplan.repository;

import com.cove.safetyplan.model.entities.MentalHealthService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMentalHealthServiceRepository extends JpaRepository<MentalHealthService, Long> {
}
