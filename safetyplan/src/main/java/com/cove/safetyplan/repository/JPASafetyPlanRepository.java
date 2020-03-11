package com.cove.safetyplan.repository;

import com.cove.safetyplan.model.entities.Safetyplan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPASafetyPlanRepository extends JpaRepository<Safetyplan, Long> {
    Safetyplan findByStudentId(long studentId);
    List<Safetyplan> findByClinicianId(long clinicianId);
}
