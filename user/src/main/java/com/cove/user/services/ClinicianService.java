package com.cove.user.services;

import com.cove.user.dto.model.ClinicianDTO;
import com.cove.user.dto.model.StudentDTO;

import java.util.List;

public interface ClinicianService {
    ClinicianDTO getClinicianById(long clinicianId);
    ClinicianDTO updateClinician(ClinicianDTO clinicianDTO);
    ClinicianDTO addClinician(ClinicianDTO clinicianDTO);
    List<StudentDTO> getAllStudentsForClinician(long clinicianId);
    void deleteClinician(long clicianId);
}
