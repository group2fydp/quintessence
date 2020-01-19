package com.cove.safetyplan.service;

import com.cove.safetyplan.dto.model.SafetyPlanDto;
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

    @Autowired
    private CopingStrategyService copingStrategyService;

    @Override
    public SafetyPlanDto createNewSafetyPlan(SafetyPlanDto safetyPlanDto){
        SafetyPlan safetyPlanModel = modelMapper.map(safetyPlanDto, SafetyPlan.class);
        safetyPlanModel.setVersion("1");
        return modelMapper.map(jpaSafetyPlanRepository.save(safetyPlanModel), SafetyPlanDto.class);
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
}
