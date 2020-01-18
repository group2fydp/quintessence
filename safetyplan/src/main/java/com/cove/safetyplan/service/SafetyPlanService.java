package com.cove.safetyplan.service;

import com.cove.safetyplan.dto.model.CopingStrategyDto;
import com.cove.safetyplan.dto.model.SafetyPlanDto;

import java.util.List;

public interface SafetyPlanService {

    //Safety plan related methods
    SafetyPlanDto createNewSafetyPlan(SafetyPlanDto safetyPlanDto);
    SafetyPlanDto getSafetyPlanByStudentId(long studentId);
    SafetyPlanDto updateClinicianForSafetyPlan(SafetyPlanDto safetyPlanDto);
    List<SafetyPlanDto> getSafetyplansByClinicianId(long clinicianId);

    //Coping strategy related methods
    List<CopingStrategyDto> addCopingStrategyToSafetyPlan(CopingStrategyDto copingStrategyDto);
    CopingStrategyDto updateCopingStrategy(CopingStrategyDto copingStrategyDto);
    List<CopingStrategyDto> getCopingStrategies(long safetyplanId);
}
