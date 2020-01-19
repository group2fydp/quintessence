package com.cove.safetyplan.service;

import com.cove.safetyplan.dto.model.CopingStrategyDto;
import com.cove.safetyplan.model.entities.CopingStrategy;
import com.cove.safetyplan.repository.JPACopingStrategyRepository;
import com.netflix.discovery.EurekaClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CopingStrategyServiceImpl implements CopingStrategyService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private JPACopingStrategyRepository jpaCopingStrategyRepository;

    @Override
    public List<CopingStrategyDto> addCopingStrategyToSafetyPlan(CopingStrategyDto copingStrategyDto){
        CopingStrategy newCopingStrategy = modelMapper.map(copingStrategyDto, CopingStrategy.class);
        jpaCopingStrategyRepository.save(newCopingStrategy);
        return getCopingStrategies(copingStrategyDto.getSafetyplanId());
    }

    @Override
    public CopingStrategyDto updateCopingStrategy(CopingStrategyDto copingStrategyDto){
        Optional<CopingStrategy> copingStrategy = jpaCopingStrategyRepository.findById(copingStrategyDto.getCopingStrategyId());
        if(copingStrategy.isPresent()){
            CopingStrategy copingStrategyModel = copingStrategy.get();
            copingStrategyModel.setType(copingStrategyDto.getType());
            return modelMapper.map(jpaCopingStrategyRepository.save(copingStrategyModel), CopingStrategyDto.class);
        }
        return new CopingStrategyDto();
    }

    @Override
    public List<CopingStrategyDto> getCopingStrategies(long safetyPlanId){
        List<CopingStrategyDto> copingStrategyDtos = new ArrayList<>();
        jpaCopingStrategyRepository.findBySafetyplanId(safetyPlanId)
                .forEach(strategy -> copingStrategyDtos.add(modelMapper.map(strategy, CopingStrategyDto.class)));
        return copingStrategyDtos;
    }

    @Override
    public CopingStrategyDto getCopingStrategy(long copingStrategyId){
        return modelMapper.map(jpaCopingStrategyRepository.findById(copingStrategyId).get(), CopingStrategyDto.class);
    }

    @Override
    public void deleteCopingStrategy(long copingStrategyId){
        Optional<CopingStrategy> copingStrategy = jpaCopingStrategyRepository.findById(copingStrategyId);
        if (copingStrategy.isPresent()){
            jpaCopingStrategyRepository.delete(copingStrategy.get());
        }
    }

    @Override
    public void deleteAllCopingStrategiesFromSafetyPlan(long safetyplanId){
        List<CopingStrategy> copingStrategies = jpaCopingStrategyRepository.findBySafetyplanId(safetyplanId);

        if(!copingStrategies.isEmpty())
            copingStrategies.forEach(copingStrategy -> jpaCopingStrategyRepository.delete(copingStrategy));
    }
}
