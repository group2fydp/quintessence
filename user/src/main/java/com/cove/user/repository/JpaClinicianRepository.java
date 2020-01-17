package com.cove.user.repository;

import com.cove.user.model.entities.Clinician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClinicianRepository extends JpaRepository<Clinician, Long> {

}
