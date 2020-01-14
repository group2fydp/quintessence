package com.cove.safetyplan.controller;

import com.cove.safetyplan.dto.model.CopingStrategyDto;
import com.cove.safetyplan.dto.model.SafetyPlanDto;
import com.cove.safetyplan.dto.model.UserDto;
import com.cove.safetyplan.dto.response.Response;
import com.cove.safetyplan.service.SafetyPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/safety_plan_api/v1/safetyPlan")
public class SafetyPlanController {
    @Autowired
    private SafetyPlanService safetyPlanService;

    //Safety plan endpoints

    /**
     * Get all members of a safety plan
     * @param id {Safety plan id}
     * @return {Safety plan dto, list of coping strategy dtos, student dto, clinician dto, version, reasons to live, warning signs, etc}
     */
    @GetMapping("/{id}")
    public Response getSafetyPlan(@PathVariable Long id){

        SafetyPlanDto response = safetyPlanService.getSafetyPlan(id);

        return Response.ok();
    }

    /**
     * Create a new safety plan for a patient
     * This should happen automatically when a student profile is initiated.
     * @param safetyPlanDto {Valid safety plan request}
     * @return {Safety Plan Dto}
     */
    @PostMapping("/new")
    public Response newSafetyPlan(@RequestBody @Valid SafetyPlanDto safetyPlanDto){

        SafetyPlanDto response = safetyPlanService.createNewSafetyPlan(safetyPlanDto);

        return Response.ok().setPayload(response);
    }

    /**
     * Get the clinician associated with the safety plan
     * @param id {Safety plan id}
     * @return {Clinician}
     */
    @GetMapping("/{id}/clinician")
    public Response getClinician(@PathVariable Long id){

        UserDto response = safetyPlanService.getClinician(id);

        return Response.ok().setPayload(response);
    }

    /**
     * Update the clinician assigned to the safety plan
     * @param id {Safety plan id}
     * @param clinician {The new clinician}
     * @return {clinitian Dto}
     */
    @PutMapping("/{id}/clinician")
    public Response changeSafetyPlanClinician(@PathVariable Long id, @RequestBody @Valid UserDto clinician){
        //TODO: Check that safety plan exists, if clinician exists, update the clinician id, return

        UserDto response = safetyPlanService.updateClinician(id, clinician);

        return Response.ok().setPayload(response);
    }

    /**
     *
     * TODO:Figure out if this even valid
     * @param safetyPlanDto {Valid safety plan request}
     * @return {Success}
     */
    @DeleteMapping("/")
    public Response deleteSafetyPlan(@RequestBody @Valid SafetyPlanDto safetyPlanDto){
        return Response.ok();
    }

    //Coping strategy endpoints

    /**
     * Add a new coping strategy
     * @param copingStrategyDto {Coping strategy}
     * @param id {Safety plan id}
     * @return {List of safety plan coping strategies}
     */
    @PostMapping("/{id}/copingStrategies")
    public Response addCopingStrategy(@RequestBody @Valid CopingStrategyDto copingStrategyDto, @PathVariable Long id){

        List<CopingStrategyDto> response =safetyPlanService.addCopingStrategy(id, copingStrategyDto);

        return Response.ok().setPayload(response);
    }

    /**
     * Update a coping strategy
     * @param copingStrategyDto {Coping strategy to update}
     * @param id {Safetyplan id}
     * @return {Coping strategy dto}
     */
    @PutMapping("/{id}/copingStrategies")
    public Response updateCopingStrategy(@RequestBody @Valid CopingStrategyDto copingStrategyDto, @PathVariable Long id){

        CopingStrategyDto response = safetyPlanService.updateCopingStrategy(id, copingStrategyDto);
        return Response.ok();
    }

    /**
     * Get Coping strategy by id
     * @param id {Safety plan id}
     * @param cs_id {Coping strategy id}
     * @return {Coping strategy dto}
     */
    @GetMapping("/{id}/copingStrategies/{cs_id}")
    public Response getCopingStrategyById(@PathVariable Long id, @PathVariable Long cs_id){
        return Response.ok();
    }

    /**
     * Get all coping strategies for a safety plan
     * @param id {Safety plan id}
     * @return {List of coping strategy dtos}
     */
    @GetMapping("/{id}/copingStrategies")
    public Response getCopingStrategies(@PathVariable Long id){
        return Response.ok();
    }

    /**
     * Remove a coping strategy from a safety plan
     * @param id {Safety plan id}
     * @param cs_id {Coping strategy id}
     * @return {Response code}
     */
    @DeleteMapping("{id}/copingStrategies/{cs_id}")
    public Response removeCopingStrategyFromSafetyPlan(@PathVariable Long id, @PathVariable Long cs_id){
        return Response.ok();
    }

    /**
     * Remove all coping strategies from a safety plan
     * @param id {Safety plan id}
     * @return {Response code}
     */
    @DeleteMapping("{id}/copingStrategies/")
    public Response removeAllCopingStrategiesFromSafetyPlan(@PathVariable Long id){
        return Response.ok();
    }
}
