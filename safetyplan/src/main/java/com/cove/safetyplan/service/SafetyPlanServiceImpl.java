package com.cove.safetyplan.service;

import com.cove.safetyplan.dto.model.CopingStrategyDto;
import com.cove.safetyplan.dto.model.SafetyPlanDto;
import com.cove.safetyplan.dto.model.UserDto;
import com.cove.safetyplan.model.CopingStrategy;
import com.cove.safetyplan.model.SafetyPlan;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SafetyPlanServiceImpl implements SafetyPlanService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SafetyPlanDto createNewSafetyPlan(SafetyPlanDto safetyPlanDto){

        //TODO: Check if safety plan exists in repo (check by student ID or clinician ID)
        SafetyPlan safetyPlanModel = new SafetyPlan()
                //TODO:Generate unique ID
                .setClinician_id(safetyPlanDto.getClinician_id())
                .setStudent_id(safetyPlanDto.getStudent_id());

        return modelMapper.map(safetyPlanModel, SafetyPlanDto.class);
        //TODO:Implement exception handling
    }

    @Override
    public SafetyPlanDto getSafetyPlan(Long safety_plan_id){
        //TODO: Check that safety plan exists, get all members of it (warning signs, reasons to live, coping strategies, etc...)
        SafetyPlanDto temp = new SafetyPlanDto();
        return temp;
        //TODO:Implement exception handling
    }

    @Override
    public UserDto getClinician(Long safety_plan_id){

        UserDto clinician = new UserDto();
        return clinician;
    }

    @Override
    public UserDto updateClinician(Long safety_plan_id, UserDto clinician){
        //TODO: Check that safety plan exists, update clinician
        UserDto updated_clinician = new UserDto();
        return updated_clinician;
    }

    // Coping Strategy methods
    @Override
    public List<CopingStrategyDto> addCopingStrategy(Long safety_plan_id, CopingStrategyDto copingStrategyDto){
        List<CopingStrategyDto> strategies = new ArrayList<>();

        //TODO: Get all coping strategies for the safety plan
        strategies.add(new CopingStrategyDto());

        return strategies;
    }

    @Override
    public CopingStrategyDto updateCopingStrategy(Long safety_plan_id, CopingStrategyDto copingStrategyDto){
        //TODO: Find coping strategy to update
        CopingStrategyDto updatedCopingStrategy = new CopingStrategyDto()
                .setType(copingStrategyDto.getType())
                .setSafety_plan_id(safety_plan_id);

        return updatedCopingStrategy;
    }
}
