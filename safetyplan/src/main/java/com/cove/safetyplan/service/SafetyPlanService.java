package com.cove.safetyplan.service;

import com.cove.safetyplan.dto.model.CopingStrategyDto;
import com.cove.safetyplan.dto.model.SafetyPlanDto;

import java.util.List;

public interface SafetyPlanService {

    //Safety plan related methods
    SafetyPlanDto createNewSafetyPlan(SafetyPlanDto safetyPlanDto);
    SafetyPlanDto getSafetyPlan(Long safety_plan_id);
    SafetyPlanDto updateClinicianForSafetyPlan(SafetyPlanDto safetyPlanDto);

    //Coping strategy related methods
    List<CopingStrategyDto> addCopingStrategy(CopingStrategyDto copingStrategyDto);
    CopingStrategyDto updateCopingStrategy(CopingStrategyDto copingStrategyDto);
    List<CopingStrategyDto> getCopingStrategies(Long safety_plan_id);
}
