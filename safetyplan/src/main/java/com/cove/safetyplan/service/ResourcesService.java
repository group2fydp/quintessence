package com.cove.safetyplan.service;

import com.cove.safetyplan.dto.model.*;
import com.cove.safetyplan.model.entities.InstitutionLocation;
import com.cove.safetyplan.model.entities.MentalHealthService;

import java.util.List;

public interface ResourcesService {
    List<HelplineDTO> getHelplines();
    HelplineDTO createHelpline(HelplineDTO helplineDTO);
    MentalHealthService createMentalHealthService(MentalHealthServiceDTO mentalHealthServiceDTO);
    List<MentalHealthServiceDTO> getMentalHealthServicesForInstitutionLocation(long institutionLocationId);
    InstitutionDTO createInstitution(InstitutionDTO institutionDTO);
    InstitutionDTO getInstitution(long institutionId);
    InstitutionLocationDTO createInstitutionLocation(InstitutionLocationDTO institutionLocationDTO);
    List<InstitutionLocation> getAllInstitutionLocation();
    List<SocialLocationDTO> getSocialLocationForSafetyPlan(long safetyplanId);
    SocialLocationDTO createSocialLocation(SocialLocationDTO socialLocationDTO);
}
