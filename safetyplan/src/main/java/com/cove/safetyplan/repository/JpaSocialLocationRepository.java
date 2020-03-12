package com.cove.safetyplan.repository;

import com.cove.safetyplan.model.entities.Safetyplan;
import com.cove.safetyplan.model.entities.SocialLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaSocialLocationRepository extends JpaRepository<SocialLocation, Long> {
    List<SocialLocation> findAllBySafetyplan(Safetyplan safetyplan);
}
