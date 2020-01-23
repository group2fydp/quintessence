package com.cove.safetyplan.controller;

import com.cove.safetyplan.dto.model.CopingStrategyDto;
import com.cove.safetyplan.dto.model.SafetyPlanDto;
import com.cove.safetyplan.dto.response.Response;
import com.cove.safetyplan.service.CopingStrategyService;
import com.cove.safetyplan.service.SafetyPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/safety_plan_api/v1/safetyplan", produces = MediaType.APPLICATION_JSON_VALUE)
public class SafetyPlanController {
    @Autowired
    private SafetyPlanService safetyPlanService;

    //Safety plan endpoints

    /**
     * TODO: Get user service endpoints to grab all of the safety plan data
     * Get all members of a safety plan
     * @param id {Safety plan id}
     * @return {Safety plan dto, list of coping strategy dtos, student dto, clinician dto, version, reasons to live, warning signs, etc}
     */
//    @GetMapping("/{id}")
//    public Response getSafetyPlan(@PathVariable Long id){
//
//        SafetyPlanDto safetyPlan = safetyPlanService.getSafetyPlan(id);
//
//        List<CopingStrategyDto> copingStrategyDtos = safetyPlanService.getCopingStrategies(id);
//
//        JSONObject response = new JSONObject();
//        response.put("safetyPlan", safetyPlan);
//        response.put("copingStrategies", copingStrategyDtos);
//
//        return Response.ok().setPayload(response);
//    }

    /**
     * Get safetyplan dto from student id
     * @param id {Student id}
     * @return {Safety plan dto}
     */
    @GetMapping("/student/{id}")
    public Response getSafetyPlanByStudentId(@PathVariable Long id){
        return Response.ok().setPayload(safetyPlanService.getSafetyPlanByStudentId(id));
    }

    /**
     *
     * @param id {clinician id}
     * @return {list of safety plan dtos}
     */
    @GetMapping("/clinician/{id}")
    public Response getSafetyplansByClinicianId(@PathVariable long id){
        return Response.ok().setPayload((safetyPlanService.getSafetyplansByClinicianId(id)));
    }

    /**
     * Create a new safety plan for a patient
     * This should happen automatically when a student profile is initiated.
     * @param safetyPlanDto {Valid safety plan request}
     * @return {Safety Plan Dto}
     */
    @PostMapping("/new")
    public Response newSafetyPlan(@RequestBody SafetyPlanDto safetyPlanDto){
        return Response.ok().setPayload(safetyPlanService.createNewSafetyPlan(safetyPlanDto));
    }

    /**
     * Update the clinician assigned to the safety plan
     * @param safetyPlanDto {The new safety plan dto}
     * @return {clinician Dto}
     */
    @PutMapping("/clinician")
    public Response updateClinicianForSafetyPlan(@RequestBody @Valid SafetyPlanDto safetyPlanDto){
        return Response.ok().setPayload(safetyPlanService.updateClinicianForSafetyPlan(safetyPlanDto));
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
}
