package com.cove.safetyplan.controller;

import com.cove.safetyplan.dto.model.*;
import com.cove.safetyplan.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/resources", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResourcesController {
    @Autowired
    private ResourcesService resourcesService;

    @GetMapping("/helplines")
    public List<HelplineDTO> getHelplines(){
        return resourcesService.getHelplines();
    }

    @PostMapping("/helpline/new")
    public HelplineDTO createHelpline(@RequestBody HelplineDTO helplineDTO){
        return resourcesService.createHelpline(helplineDTO);
    }
    @PostMapping("/mentalHealthService/new")
    public MentalHealthServiceDTO createMentalHealthService(@RequestBody MentalHealthServiceDTO mentalHealthServiceDTO){
        return resourcesService.createMentalHealthService(mentalHealthServiceDTO);
    }

    @GetMapping("/institutionLocations")
    public List<InstitutionLocationResponseDTO> getInstitutionLocations(){
        return resourcesService.getAllInstitutionLocations();
    }

    @PostMapping("/institutionLocation/new")
    public InstitutionLocationResponseDTO createInstitutionLocation(@RequestBody InstitutionLocationRequestDTO institutionLocationRequestDTO){
        return resourcesService.createInstitutionLocation(institutionLocationRequestDTO);
    }

    @PostMapping("/institution/new")
    public InstitutionDTO createInstitution(@RequestBody InstitutionDTO institutionDTO){
        return resourcesService.createInstitution(institutionDTO);
    }

    @GetMapping("/institution/{id}")
    public InstitutionDTO getInstitution(@PathVariable long id){
        return resourcesService.getInstitution(id);
    }

    @GetMapping("/socialLocations/{safetyPlanId}")
    public List<SocialLocationDTO> getSocialLocations(@PathVariable long safetyPlanId){
        return resourcesService.getSocialLocationForSafetyPlan(safetyPlanId);
    }

    @PostMapping("/socialLocation/new")
    public SocialLocationDTO createSocialLocation(@RequestBody SocialLocationDTO socialLocationDTO){
        return resourcesService.createSocialLocation(socialLocationDTO);
    }

    @PutMapping("/socialLocation/update")
    public SocialLocationDTO updateSocialLocation(@RequestBody SocialLocationDTO socialLocationDTO){
        return resourcesService.updateSocialLocation(socialLocationDTO);
    }

}
