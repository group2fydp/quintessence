package com.cove.safetyplan.service;

import com.cove.safetyplan.dto.model.CopingStrategyDto;
import com.cove.safetyplan.dto.model.SafetyPlanDto;
import com.cove.safetyplan.dto.model.UserDto;

import java.util.List;

public interface SafetyPlanService {

    //Safety plan related methods
    SafetyPlanDto createNewSafetyPlan(SafetyPlanDto safetyPlanDto);
    SafetyPlanDto getSafetyPlan(Long safety_plan_id);
    UserDto getClinician(Long safety_plan_id);
    UserDto updateClinician(Long safety_plan_id, UserDto clinician);

    //Coping strategy related methods
    List<CopingStrategyDto> addCopingStrategy(Long safety_plan_id, CopingStrategyDto copingStrategyDto);
    CopingStrategyDto updateCopingStrategy(Long safety_plan_id, CopingStrategyDto copingStrategyDto);
}
