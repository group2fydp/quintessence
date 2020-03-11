package com.cove.safetyplan.repository;

import com.cove.safetyplan.model.entities.Helpline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaHelplineRepository extends JpaRepository<Helpline, Long> {
}
