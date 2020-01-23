package com.cove.user.repository;

import com.cove.user.model.entities.Clinician;
import com.cove.user.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaStudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByClinician(Clinician clinician);
    Student findByStudentNumber(long studentNumber);
    Student findBySafetyplanId(long safetyPlanId);
}
