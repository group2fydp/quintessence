package com.cove.safetyplan.repository;

import com.cove.safetyplan.model.entities.CopingStrategy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPACopingStrategyRepository extends JpaRepository<CopingStrategy, Long> {
    List<CopingStrategy> findBySafetyplanId(long safetyplanId);
}
