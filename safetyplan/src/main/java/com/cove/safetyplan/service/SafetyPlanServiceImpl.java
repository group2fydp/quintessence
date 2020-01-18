package com.cove.safetyplan.service;

import com.cove.safetyplan.dto.model.CopingStrategyDto;
import com.cove.safetyplan.dto.model.SafetyPlanDto;
import com.cove.safetyplan.model.entities.CopingStrategy;
import com.cove.safetyplan.model.entities.SafetyPlan;
import com.cove.safetyplan.repository.JPACopingStrategyRepository;
import com.cove.safetyplan.repository.JPASafetyPlanRepository;
import com.netflix.discovery.EurekaClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SafetyPlanServiceImpl implements SafetyPlanService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private JPASafetyPlanRepository jpaSafetyPlanRepository;

    @Autowired
    private JPACopingStrategyRepository jpaCopingStrategyRepository;

    @Override
    public SafetyPlanDto createNewSafetyPlan(SafetyPlanDto safetyPlanDto){
        SafetyPlan safetyPlanModel = modelMapper.map(safetyPlanDto, SafetyPlan.class);
        safetyPlanModel.setVersion("1");
        return modelMapper.map(jpaSafetyPlanRepository.save(safetyPlanModel), SafetyPlanDto.class);

        //TODO:Implement exception handling
    }

    @Override
    public SafetyPlanDto getSafetyPlanByStudentId(long studentId){
        SafetyPlan safetyPlanModel = jpaSafetyPlanRepository.findByStudentId(studentId);
        return modelMapper.map(safetyPlanModel, SafetyPlanDto.class);
    }

    @Override
    public List<SafetyPlanDto> getSafetyplansByClinicianId(long clinicianId){
        List<SafetyPlan> safetyplans = jpaSafetyPlanRepository.findByClinicianId(clinicianId);
        List<SafetyPlanDto> safetyPlanDtos = new ArrayList<>();
        safetyplans.forEach(plan -> safetyPlanDtos.add(modelMapper.map(plan, SafetyPlanDto.class)));

        return safetyPlanDtos;
    }

    @Override
    public SafetyPlanDto updateClinicianForSafetyPlan(SafetyPlanDto safetyPlanDto){
        Optional<SafetyPlan> safetyPlan = Optional.ofNullable(jpaSafetyPlanRepository.findByStudentId(safetyPlanDto.getStudentId()));
        if(safetyPlan.isPresent()){
            SafetyPlan safetyPlanModel = safetyPlan.get();
            safetyPlanModel.setClinicianId(safetyPlanDto.getClinicianId());
            return modelMapper.map(jpaSafetyPlanRepository.save(safetyPlanModel), SafetyPlanDto.class);
        }
        return new SafetyPlanDto();
    }

    // Coping Strategy methods
    @Override
    public List<CopingStrategyDto> addCopingStrategyToSafetyPlan(CopingStrategyDto copingStrategyDto){
        CopingStrategy newCopingStrategy = modelMapper.map(copingStrategyDto, CopingStrategy.class);
        jpaCopingStrategyRepository.save(newCopingStrategy);
        List<CopingStrategyDto> strategyDtos = new ArrayList<>();
        List<CopingStrategy> strategies = jpaCopingStrategyRepository.findBySafetyplanId(copingStrategyDto.getSafetyplanId());
        strategies.forEach(strategy -> strategyDtos.add(modelMapper.map(strategy, CopingStrategyDto.class)));
        return strategyDtos;
    }

    @Override
    public CopingStrategyDto updateCopingStrategy(CopingStrategyDto copingStrategyDto){
        //TODO: Find coping strategy to update
        CopingStrategy updatedCopingStrategy = new CopingStrategy()
                .setType(copingStrategyDto.getType());
        return modelMapper.map(updatedCopingStrategy, CopingStrategyDto.class);
    }

    @Override
    public List<CopingStrategyDto> getCopingStrategies(long safety_plan_id){

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
