package com.cove.safetyplan.service;

import com.cove.safetyplan.dto.model.HelplineDTO;
import com.cove.safetyplan.dto.model.InstitutionDTO;
import com.cove.safetyplan.dto.model.InstitutionLocationDTO;
import com.cove.safetyplan.dto.model.MentalHealthServiceDTO;
import com.cove.safetyplan.model.entities.InstitutionLocation;
import com.cove.safetyplan.model.entities.MentalHealthService;

import java.util.List;

public interface ResourcesService {
    List<HelplineDTO> getHelplines();
    HelplineDTO createHelpline(HelplineDTO helplineDTO);
    MentalHealthService createMentalHealthService(MentalHealthServiceDTO mentalHealthServiceDTO);
    List<MentalHealthServiceDTO> getMentalHealthServicesForInstitutionLocation(long institutionLocationId);4
    InstitutionDTO createInstitution(InstitutionDTO institutionDTO);
    InstitutionDTO getInstitution(long institutionId);
    InstitutionLocationDTO createInstitutionLocation(InstitutionLocationDTO institutionLocationDTO);
    List<InstitutionLocation> getAllInstitutionLocation();
}
