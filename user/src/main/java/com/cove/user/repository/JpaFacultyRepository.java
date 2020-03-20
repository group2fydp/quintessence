package com.cove.user.repository;

import com.cove.user.model.entities.Faculty;
import com.cove.user.model.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaFacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findBySchool(School school);
}
