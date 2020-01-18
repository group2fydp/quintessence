package com.cove.safetyplan.repository;

import com.cove.safetyplan.model.entities.SafetyPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPASafetyPlanRepository extends JpaRepository<SafetyPlan, Long> {
    SafetyPlan findByStudentId(long studentId);
    List<SafetyPlan> findByClinicianId(long clinicianId);
}
