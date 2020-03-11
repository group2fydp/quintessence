package com.cove.safetyplan.service;

import com.cove.safetyplan.dto.model.SafetyPlanDto;
import com.cove.safetyplan.exception.CoveException;
import com.cove.safetyplan.exception.EntityType;
import com.cove.safetyplan.exception.ExceptionType;
import com.cove.safetyplan.model.entities.Safetyplan;
import com.cove.safetyplan.repository.JPACopingStrategyRepository;
import com.cove.safetyplan.repository.JPASafetyPlanRepository;
import com.netflix.discovery.EurekaClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.cove.safetyplan.exception.EntityType.*;
import static com.cove.safetyplan.exception.ExceptionType.*;

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

    @Autowired
    private CopingStrategyService copingStrategyService;

    @Override
    public SafetyPlanDto createNewSafetyPlan(SafetyPlanDto safetyPlanDto){
        Safetyplan safetyplanModel = modelMapper.map(safetyPlanDto, Safetyplan.class);
        return modelMapper.map(jpaSafetyPlanRepository.save(safetyplanModel), SafetyPlanDto.class);
    }

    @Override
    public SafetyPlanDto getSafetyPlanByStudentId(long studentId){
        Optional<Safetyplan> safetyPlan = Optional.ofNullable(jpaSafetyPlanRepository.findByStudentId(studentId));
        if(safetyPlan.isPresent()){
            return modelMapper.map(safetyPlan.get(), SafetyPlanDto.class);
        }
        throw exception(SAFETYPLAN, ENTITY_NOT_FOUND, Long.toString(studentId));
    }

    @Override
    public List<SafetyPlanDto> getSafetyplansByClinicianId(long clinicianId){
        List<Safetyplan> safetyplans = jpaSafetyPlanRepository.findByClinicianId(clinicianId);
        List<SafetyPlanDto> safetyPlanDtos = new ArrayList<>();
        safetyplans.forEach(plan -> safetyPlanDtos.add(modelMapper.map(plan, SafetyPlanDto.class)));
        return safetyPlanDtos;
    }

    @Override
    public SafetyPlanDto updateClinicianForSafetyPlan(SafetyPlanDto safetyPlanDto){
        Optional<Safetyplan> safetyPlan = jpaSafetyPlanRepository.findById(safetyPlanDto.getSafetyplanId());
        if(safetyPlan.isPresent()){
            Safetyplan safetyplanModel = safetyPlan.get();
            safetyplanModel.setClinicianId(safetyPlanDto.getClinicianId());
            return modelMapper.map(jpaSafetyPlanRepository.save(safetyplanModel), SafetyPlanDto.class);
        }
        throw exception(SAFETYPLAN, ENTITY_NOT_FOUND, Long.toString(safetyPlanDto.getSafetyplanId()));
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return CoveException.throwException(entityType, exceptionType, args);
    }
}
