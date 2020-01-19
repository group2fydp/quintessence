package com.cove.user.controllers;

import com.cove.user.dto.model.ClinicianDTO;
import com.cove.user.services.ClinicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/clinician")
public class ClinicianController {
    @Autowired
    private Environment environment;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private ClinicianService clinicianService;

    @RequestMapping("/{id}")
    public ClinicianDTO getClinician(@PathVariable final int id){
        return clinicianService.getClinicianById(id);
    }

    @PostMapping("/new")
    public ClinicianDTO createClinician(@RequestBody ClinicianDTO clinicianDTO){
        return clinicianService.addClinician(clinicianDTO);
    }

    @PutMapping("/update")
    public ClinicianDTO updateClinician(@RequestBody ClinicianDTO clinicianDTO) {
        return clinicianService.updateClinician(clinicianDTO);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteClinician(@PathVariable final int id){
        clinicianService.deleteClinician(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

}
