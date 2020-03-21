package com.cove.user.services;

import com.cove.user.dto.model.*;

import java.util.List;
import java.util.Optional;

public interface ClinicianService {
    ClinicianDTO getClinicianById(long clinicianId);
    Optional<ClinicianDTO> getClinicianByUsername(String username);
    ClinicianDTO updateClinician(ClinicianDTO clinicianDTO);
    void addClinician(ClinicianDTO clinicianDTO);
    List<StudentDTO> getAllStudentsForClinician(long clinicianId);
    String deleteClinician(long clicianId);
    List<SchoolDTO> getAllSchools();
    List<FacultyDTO> getAllFacultiesForSchool(long schoolId);
    List<ProgramDTO> getAllProgramsForFaculty(long facultyId);
}
