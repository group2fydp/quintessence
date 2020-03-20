package com.cove.user.controllers;

import com.cove.user.dto.model.*;
import com.cove.user.services.ClinicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clinician")
public class ClinicianController {
    @Autowired
    private ClinicianService clinicianService;

    @RequestMapping("/{username}")
    public ClinicianDTO getClinician(@PathVariable final String username){
        return clinicianService.getClinicianByUsername(username).get();
    }

    @RequestMapping("/{clinicianId}/students")
    public List<StudentDTO> getAllStudentsForClinician(@PathVariable final long clinicianId){
        return clinicianService.getAllStudentsForClinician(clinicianId);
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

    @GetMapping("/schools")
    public List<SchoolDTO> getAllSchools(){
        return clinicianService.getAllSchools();
    }

    @RequestMapping("/{schoolId}/faculties")
    public List<FacultyDTO> getAllFacultiesForSchool(@PathVariable final long schoolId){
        return clinicianService.getAllFacultiesForSchool(schoolId);
    }

    @RequestMapping("/{facultyId}/programs")
    public List<ProgramDTO> getAllProgramsForFaculty(@PathVariable final long facultyId){
        return clinicianService.getAllProgramsForFaculty(facultyId);
    }
}
