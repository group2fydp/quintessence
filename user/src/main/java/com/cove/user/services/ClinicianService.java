package com.cove.user.services;

import com.cove.user.dto.model.ClinicianDTO;
import com.cove.user.dto.model.StudentDTO;
import com.cove.user.exception.UserNotFoundException;

import java.util.List;

public interface ClinicianService {
    ClinicianDTO getClinicianById(long clinicianId) throws UserNotFoundException;
    ClinicianDTO updateClinician(ClinicianDTO clinicianDTO) throws UserNotFoundException;
    ClinicianDTO addClinician(ClinicianDTO clinicianDTO);
    List<StudentDTO> getAllStudentsForClinician(long clinicianId);
    void deleteClinician(long clicianId);
}
