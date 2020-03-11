package com.cove.user.repository;

import com.cove.user.model.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFacultyRepository extends JpaRepository<Faculty, Long> {
}
