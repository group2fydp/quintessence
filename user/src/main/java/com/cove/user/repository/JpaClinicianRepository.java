package com.cove.user.repository;

import com.cove.user.model.entities.Clinician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaClinicianRepository extends JpaRepository<Clinician, Long> {
    Optional<Clinician> findByUsername(String username);
}
