package com.cove.safetyplan.service;

import com.cove.safetyplan.dto.model.CopingStrategyDto;
import com.cove.safetyplan.dto.model.InstructionDTO;

import java.util.List;

public interface CopingStrategyService {
    List<CopingStrategyDto> addCopingStrategyToSafetyPlan(CopingStrategyDto copingStrategyDto);
    CopingStrategyDto updateCopingStrategy(CopingStrategyDto copingStrategyDto);
    List<CopingStrategyDto> getCopingStrategies(long safetyplanId);
    CopingStrategyDto getCopingStrategy(long copingStrategyId);
    void deleteCopingStrategy(long copingStrategyId);
    void deleteAllCopingStrategiesFromSafetyPlan(long safetyplanId);
    InstructionDTO createInstruction(InstructionDTO instructionDTO);
    List<InstructionDTO> getInstructionsByCopingStrategy(long copingStrategyId);
    void deleteInstruction(long instructionId);
}
