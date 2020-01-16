package com.cove.safetyplan.service;

import com.cove.safetyplan.dto.model.CopingStrategyDto;
import com.cove.safetyplan.dto.model.SafetyPlanDto;
import com.cove.safetyplan.model.CopingStrategy;
import com.cove.safetyplan.model.SafetyPlan;
import com.netflix.discovery.EurekaClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class SafetyPlanServiceImpl implements SafetyPlanService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EurekaClient eurekaClient;

    @Override
    public SafetyPlanDto createNewSafetyPlan(SafetyPlanDto safetyPlanDto){

        //TODO: Check if safety plan exists in repo (check by student ID or clinician ID)
        SafetyPlan safetyPlanModel = new SafetyPlan()
                //TODO:Generate unique ID
                .setClinicianId(safetyPlanDto.getClinicianId())
                .setStudentId(safetyPlanDto.getStudentId());

        return modelMapper.map(safetyPlanModel, SafetyPlanDto.class);
        //TODO:Implement exception handling
    }

    @Override
    public SafetyPlanDto getSafetyPlan(Long safety_plan_id){
        //TODO: Check that safety plan exists, get all members of it (warning signs, reasons to live, coping strategies, etc...)

        Long clinician_id = new Random().nextLong();
        Long student_id = new Random().nextLong();

        SafetyPlan safetyPlanModel = new SafetyPlan()
                .setClinicianId(clinician_id)
                .setStudentId(student_id);

        return modelMapper.map(safetyPlanModel, SafetyPlanDto.class);
    }

    @Override
    public SafetyPlanDto updateClinicianForSafetyPlan(SafetyPlanDto safetyPlanDto){
        //TODO: Check that safety plan exists
        SafetyPlan safetyPlanModel = new SafetyPlan()
                .setClinicianId(safetyPlanDto.getClinicianId());

        //TODO: Save safety plan

        return modelMapper.map(safetyPlanModel, SafetyPlanDto.class);
    }

    // Coping Strategy methods
    @Override
    public List<CopingStrategyDto> addCopingStrategy(CopingStrategyDto copingStrategyDto){
        List<CopingStrategyDto> strategies = new ArrayList<>();

        //TODO: Get all coping strategies for the safety plan
        strategies.add(new CopingStrategyDto());

        return strategies;
    }

    @Override
    public CopingStrategyDto updateCopingStrategy(CopingStrategyDto copingStrategyDto){
        //TODO: Find coping strategy to update
        CopingStrategy updatedCopingStrategy = new CopingStrategy()
                .setType(copingStrategyDto.getType());
        return modelMapper.map(updatedCopingStrategy, CopingStrategyDto.class);
    }

    @Override
    public List<CopingStrategyDto> getCopingStrategies(Long safety_plan_id){

        List<CopingStrategyDto> list = new ArrayList<>();
        CopingStrategyDto copingStrategyDto1 = new CopingStrategyDto()
                .setType("social")
                .setSafetyplanId(safety_plan_id);
        CopingStrategyDto copingStrategyDto2 = new CopingStrategyDto()
                .setType("crisis")
                .setSafetyplanId(safety_plan_id);
        list.add(copingStrategyDto1);
        list.add(copingStrategyDto2);

        return list;
    }
}
