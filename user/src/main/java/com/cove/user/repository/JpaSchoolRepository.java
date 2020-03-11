package com.cove.user.repository;

import com.cove.user.model.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSchoolRepository extends JpaRepository<School, Long> {
}
