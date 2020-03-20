package com.cove.safetyplan.repository;

import com.cove.safetyplan.model.entities.CopingStrategy;
import com.cove.safetyplan.model.entities.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaInstructionRepository extends JpaRepository<Instruction, Long> {
    Optional<List<Instruction>> findByCopingStrategy(CopingStrategy copingStrategy);
}

