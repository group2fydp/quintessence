package com.cove.user.repository;

import com.cove.user.model.entities.Faculty;
import com.cove.user.model.entities.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaProgramRepository extends JpaRepository<Program, Long> {
    List<Program> findByFaculty(Faculty faculty);
}
