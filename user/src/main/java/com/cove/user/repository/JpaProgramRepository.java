package com.cove.user.repository;

import com.cove.user.model.entities.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProgramRepository extends JpaRepository<Program, Long> {
}
