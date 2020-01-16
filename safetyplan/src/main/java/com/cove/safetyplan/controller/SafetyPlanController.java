package com.cove.safetyplan.controller;

import com.cove.safetyplan.dto.model.CopingStrategyDto;
import com.cove.safetyplan.dto.model.SafetyPlanDto;
import com.cove.safetyplan.dto.response.Response;
import com.cove.safetyplan.service.SafetyPlanService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/safety_plan_api/v1/safetyPlan", produces = MediaType.APPLICATION_JSON_VALUE)
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

        SafetyPlanDto safetyPlan = safetyPlanService.getSafetyPlan(id);

        List<CopingStrategyDto> copingStrategyDtos = safetyPlanService.getCopingStrategies(id);

        JSONObject response = new JSONObject();
        response.put("safetyPlan", safetyPlan);
        response.put("copingStrategies", copingStrategyDtos);

        return Response.ok().setPayload(response);
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
     * Update the clinician assigned to the safety plan
     * @param safetyPlanDto {The new safety plan dto}
     * @return {clinitian Dto}
     */
    @PutMapping("/{id}/clinician")
    public Response updateClinicianForSafetyPlan(@RequestBody @Valid SafetyPlanDto safetyPlanDto){
        //TODO: Check that safety plan exists, if clinician exists, update the clinician id, return

        SafetyPlanDto response = safetyPlanService.updateClinicianForSafetyPlan(safetyPlanDto);

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

        List<CopingStrategyDto> response =safetyPlanService.addCopingStrategy(copingStrategyDto);

        return Response.ok().setPayload(response);
    }

    /**
     * Update a coping strategy
     * @param copingStrategyDto {Coping strategy to update}
     * @return {Coping strategy dto}
     */
    @PutMapping("/copingStrategies")
    public Response updateCopingStrategy(@RequestBody @Valid CopingStrategyDto copingStrategyDto){

        CopingStrategyDto response = safetyPlanService.updateCopingStrategy(copingStrategyDto);
        return Response.ok();
    }

    /**
     * Get Coping strategy by id
     * @param cs_id {Coping strategy id}
     * @return {Coping strategy dto}
     */
    @GetMapping("/copingStrategies/{cs_id}")
    public Response getCopingStrategy(@PathVariable Long cs_id){
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
