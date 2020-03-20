package com.cove.safetyplan.service;

import com.cove.safetyplan.dto.model.CopingStrategyDto;
import com.cove.safetyplan.dto.model.InstructionDTO;
import com.cove.safetyplan.exception.CoveException;
import com.cove.safetyplan.exception.EntityType;
import com.cove.safetyplan.exception.ExceptionType;
import com.cove.safetyplan.model.entities.CopingStrategy;
import com.cove.safetyplan.model.entities.Instruction;
import com.cove.safetyplan.model.entities.Safetyplan;
import com.cove.safetyplan.repository.JPACopingStrategyRepository;
import com.cove.safetyplan.repository.JPASafetyPlanRepository;
import com.cove.safetyplan.repository.JpaInstitutionRepository;
import com.cove.safetyplan.repository.JpaInstructionRepository;
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
public class CopingStrategyServiceImpl implements CopingStrategyService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private JPACopingStrategyRepository jpaCopingStrategyRepository;

    @Autowired
    private JPASafetyPlanRepository jpaSafetyPlanRepository;

    @Autowired
    private JpaInstructionRepository jpaInstructionRepository;

    @Override
    public List<CopingStrategyDto> addCopingStrategyToSafetyPlan(CopingStrategyDto copingStrategyDto){
        Optional<Safetyplan> safetyPlan = jpaSafetyPlanRepository.findById(copingStrategyDto.getSafetyplanId());
        if(safetyPlan.isPresent()){
            CopingStrategy newCopingStrategy = modelMapper.map(copingStrategyDto, CopingStrategy.class);
            if (!copingStrategyDto.getInstructions().isEmpty()){
                for( InstructionDTO i : copingStrategyDto.getInstructions()){
                    Instruction instruction = modelMapper.map(i, Instruction.class);
                    jpaInstructionRepository.save(instruction);
                }
            }
            jpaCopingStrategyRepository.save(newCopingStrategy);
            return getCopingStrategies(copingStrategyDto.getSafetyplanId());
        }
        throw exception(SAFETYPLAN, ENTITY_NOT_FOUND, Long.toString(copingStrategyDto.getSafetyplanId()));
    }

    @Override
    public CopingStrategyDto updateCopingStrategy(CopingStrategyDto copingStrategyDto){
        Optional<CopingStrategy> copingStrategy = jpaCopingStrategyRepository.findById(copingStrategyDto.getCopingStrategyId());
        if(copingStrategy.isPresent()){
            CopingStrategy copingStrategyModel = copingStrategy.get();
            copingStrategyModel.setType(copingStrategyDto.getType());
            if (!copingStrategyDto.getInstructions().isEmpty()){
                List<Instruction> instructions = new ArrayList<>();
                for( InstructionDTO i : copingStrategyDto.getInstructions()){
                    Instruction instruction = jpaInstructionRepository.save(modelMapper.map(i, Instruction.class));
                    instructions.add(instruction);
                }
                copingStrategyModel.setInstructions(instructions);
            }
            return modelMapper.map(jpaCopingStrategyRepository.save(copingStrategyModel), CopingStrategyDto.class);
        }
        throw exception(COPINGSTRATEGY, ENTITY_NOT_FOUND, Long.toString(copingStrategyDto.getCopingStrategyId()));
    }

    @Override
    public List<CopingStrategyDto> getCopingStrategies(long safetyplanId){
        Optional<Safetyplan> safetyPlan = jpaSafetyPlanRepository.findById(safetyplanId);
        if(safetyPlan.isPresent()){
            List<CopingStrategyDto> copingStrategyDtos = new ArrayList<>();
            jpaCopingStrategyRepository.findBySafetyplanId(safetyplanId)
                    .forEach(strategy -> copingStrategyDtos.add(modelMapper.map(strategy, CopingStrategyDto.class)));
            return copingStrategyDtos;
        }
        throw exception(SAFETYPLAN, ENTITY_NOT_FOUND, Long.toString(safetyplanId));
    }

    @Override
    public CopingStrategyDto getCopingStrategy(long copingStrategyId){
        Optional<CopingStrategy> copingStrategy = jpaCopingStrategyRepository.findById(copingStrategyId);
        if(copingStrategy.isPresent()){
            return modelMapper.map(copingStrategy.get(), CopingStrategyDto.class);
        }
        throw exception(COPINGSTRATEGY, ENTITY_NOT_FOUND, Long.toString(copingStrategyId));
    }

    @Override
    public void deleteCopingStrategy(long copingStrategyId){
        Optional<CopingStrategy> copingStrategy = jpaCopingStrategyRepository.findById(copingStrategyId);
        if (copingStrategy.isPresent()){
            jpaCopingStrategyRepository.delete(copingStrategy.get());
        }
        throw exception(COPINGSTRATEGY, ENTITY_NOT_FOUND, Long.toString(copingStrategyId));
    }

    @Override
    public void deleteAllCopingStrategiesFromSafetyPlan(long safetyplanId){
        Optional<Safetyplan> safetyPlan = jpaSafetyPlanRepository.findById(safetyplanId);
        if(safetyPlan.isPresent()){
            List<CopingStrategy> copingStrategies = jpaCopingStrategyRepository.findBySafetyplanId(safetyplanId);

            if(!copingStrategies.isEmpty())
                copingStrategies.forEach(copingStrategy -> jpaCopingStrategyRepository.delete(copingStrategy));
        }
        throw exception(SAFETYPLAN, ENTITY_NOT_FOUND, Long.toString(safetyplanId));
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return CoveException.throwException(entityType, exceptionType, args);
    }

    @Override
    public InstructionDTO createInstruction(InstructionDTO instructionDTO){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Instruction instruction = modelMapper.map(instructionDTO, Instruction.class);
        InstructionDTO response = modelMapper.map(jpaInstructionRepository.save(instruction), InstructionDTO.class);
        response.setCopingStrategyId(instruction.getCopingStrategy().getCopingStrategyId());
        return response;
    }

    @Override
    public List<InstructionDTO> getInstructionsByCopingStrategy(long copingStrategyId) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        CopingStrategy copingStrategy = jpaCopingStrategyRepository.findById(copingStrategyId).get();
        Optional<List<Instruction>> instructions = jpaInstructionRepository.findByCopingStrategy(copingStrategy);
        List<InstructionDTO> instructionDTOS = new ArrayList<>();
        if (instructions.isPresent()){
            instructions.get().forEach(i -> instructionDTOS.add(modelMapper.map(i, InstructionDTO.class)));
        }
        return instructionDTOS;
    }

    @Override
    public void deleteInstruction(long instructionId) {
        Optional<Instruction> instruction = jpaInstructionRepository.findById(instructionId);
        if (instruction.isPresent()){
            jpaInstructionRepository.delete(instruction.get());
        }
        throw exception(INSTRUCTION, ENTITY_NOT_FOUND, Long.toString(instructionId));
    }
}
