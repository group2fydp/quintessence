package com.cove.safetyplan.service;

import com.cove.safetyplan.dto.model.CopingStrategyDto;
import com.cove.safetyplan.dto.model.SafetyPlanDto;
import com.cove.safetyplan.dto.model.UserDto;
import com.cove.safetyplan.model.CopingStrategy;
import com.cove.safetyplan.model.SafetyPlan;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import net.minidev.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
                .setClinician_id(safetyPlanDto.getClinician_id())
                .setStudent_id(safetyPlanDto.getStudent_id());

        return modelMapper.map(safetyPlanModel, SafetyPlanDto.class);
        //TODO:Implement exception handling
    }

    @Override
    public SafetyPlanDto getSafetyPlan(Long safety_plan_id){
        //TODO: Check that safety plan exists, get all members of it (warning signs, reasons to live, coping strategies, etc...)

        Long clinician_id = new Random().nextLong();
        Long student_id = new Random().nextLong();

        SafetyPlanDto temp = new SafetyPlanDto()
                .setClinician_id(clinician_id)
                .setStudent_id(student_id);

        return temp;
        //TODO:Implement exception handling
    }

    @Override
    public UserDto getClinician(Long safety_plan_id){

        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("USER-SERVICE", false);

        //TODO: Get user endpoint
        final String clinician_uri_act = instanceInfo.getHomePageUrl() + "/test-api/get";

        RestTemplate restTemplate = new RestTemplate();

        UserDto clinician = restTemplate.getForObject(clinician_uri_act, UserDto.class);

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

    @Override
    public List<CopingStrategyDto> getCopingStrategies(Long safety_plan_id){

        List<CopingStrategyDto> list = new ArrayList<>();
        CopingStrategyDto copingStrategyDto1 = new CopingStrategyDto()
                .setType("social")
                .setSafety_plan_id(safety_plan_id);
        CopingStrategyDto copingStrategyDto2 = new CopingStrategyDto()
                .setType("crisis")
                .setSafety_plan_id(safety_plan_id);
        list.add(copingStrategyDto1);
        list.add(copingStrategyDto2);

        return list;
    }
}
