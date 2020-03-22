package com.cove.safetyplan.controller;

import com.cove.safetyplan.dto.model.CopingStrategyDto;
import com.cove.safetyplan.dto.model.InstructionDTO;
import com.cove.safetyplan.dto.response.Response;
import com.cove.safetyplan.service.CopingStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/copingStrategies", produces = MediaType.APPLICATION_JSON_VALUE)
public class CopingStrategiesController {

    @Autowired
    private CopingStrategyService copingStrategyService;

    /**
     * Add a new coping strategy
     * @param copingStrategyDto {Coping strategy}
     * @return {List of safety plan coping strategies}
     */
    @PostMapping("/")
    public Response addCopingStrategy(@RequestBody @Valid CopingStrategyDto copingStrategyDto){
        return Response.ok().setPayload(copingStrategyService.addCopingStrategyToSafetyPlan(copingStrategyDto));
    }

    /**
     * Update a coping strategy
     * @param copingStrategyDto {Coping strategy to update}
     * @return {Coping strategy dto}
     */
    @PutMapping("/update")
    public Response updateCopingStrategy(@RequestBody @Valid CopingStrategyDto copingStrategyDto){
        return Response.ok().setPayload(copingStrategyService.updateCopingStrategy(copingStrategyDto));
    }

    /**
     * Get Coping strategy by id
     * @param id {Coping strategy id}
     * @return {Coping strategy dto}
     */
    @GetMapping("/{id}")
    public Response getCopingStrategy(@PathVariable Long id){
        return Response.ok().setPayload(copingStrategyService.getCopingStrategy(id));
    }

    /**
     * Get all coping strategies for a safety plan
     * @param id {Safety plan id}
     * @return {List of coping strategy dtos}
     */
    @GetMapping("/{id}/all")
    public Response getCopingStrategies(@PathVariable Long id){
        return Response.ok().setPayload(copingStrategyService.getCopingStrategies(id));
    }

    /**
     * Remove a coping strategy from a safety plan
     * @param id {Coping strategy id}
     * @return {Response code}
     */
    @DeleteMapping("/{id}")
    public Response removeCopingStrategyFromSafetyPlan(@PathVariable Long id){
        copingStrategyService.deleteCopingStrategy(id);
        return Response.ok();
    }

    /**
     * Remove all coping strategies from a safety plan
     * @param id {safety plan id}
     * @return {Response ok}
     */
    @DeleteMapping("/{id}/all")
    public Response removeAllCopingStrategiesFromSafetyPlan(@PathVariable long id){
        copingStrategyService.deleteAllCopingStrategiesFromSafetyPlan(id);
        return Response.ok();
    }


    @GetMapping("/instructions/{copingStrategyId}")
    public List<InstructionDTO> getInstructionsForCopingStrategy(@PathVariable long copingStrategyId){
        return copingStrategyService.getInstructionsByCopingStrategy(copingStrategyId);
    }

    @PostMapping("/instructions/new")
    public InstructionDTO createInstruction(@RequestBody InstructionDTO instructionDTO){
        return copingStrategyService.createInstruction(instructionDTO);
    }

    @DeleteMapping("/instructions/{instructionId}")
    public Response removeInstruction(@PathVariable long instructionId){
        copingStrategyService.deleteInstruction(instructionId);
        return Response.ok();
    }
}
