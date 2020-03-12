package com.cove.safetyplan.service;

import com.cove.safetyplan.dto.model.*;

import java.util.List;

public interface ResourcesService {
    List<HelplineDTO> getHelplines();
    HelplineDTO createHelpline(HelplineDTO helplineDTO);
    MentalHealthServiceDTO createMentalHealthService(MentalHealthServiceDTO mentalHealthServiceDTO);
    List<MentalHealthServiceDTO> getMentalHealthServicesForInstitutionLocation(long institutionLocationId);
    InstitutionDTO createInstitution(InstitutionDTO institutionDTO);
    InstitutionDTO getInstitution(long institutionId);
    InstitutionLocationDTO createInstitutionLocation(InstitutionLocationDTO institutionLocationDTO);
    List<InstitutionLocationDTO> getAllInstitutionLocations();
    List<SocialLocationDTO> getSocialLocationForSafetyPlan(long safetyplanId);
    SocialLocationDTO createSocialLocation(SocialLocationDTO socialLocationDTO);
}
