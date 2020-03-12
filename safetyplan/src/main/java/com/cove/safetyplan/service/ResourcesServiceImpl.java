package com.cove.safetyplan.service;

import com.cove.safetyplan.dto.model.*;
import com.cove.safetyplan.model.entities.*;
import com.cove.safetyplan.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ResourcesServiceImpl implements ResourcesService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JpaHelplineRepository helplineRepository;

    @Autowired
    private JpaInstitutionLocationRepository institutionLocationRepository;

    @Autowired
    private JpaInstitutionRepository institutionRepository;

    @Autowired
    private JpaMentalHealthServiceRepository mentalHealthServiceRepository;

    @Autowired
    private JPASafetyPlanRepository safetyPlanRepository;

    @Autowired
    private JpaSocialLocationRepository socialLocationRepository;

    public List<HelplineDTO> getHelplines(){
        List<HelplineDTO> helplineDTOS = new ArrayList<>();
        helplineRepository.findAll().forEach(h -> helplineDTOS.add(modelMapper.map(h, HelplineDTO.class)));
        return helplineDTOS;
    }

    public HelplineDTO createHelpline(HelplineDTO helplineDTO){
        Helpline helpline = modelMapper.map(helplineDTO, Helpline.class);
        return modelMapper.map(helplineRepository.save(helpline), HelplineDTO.class);
    }

    public MentalHealthServiceDTO createMentalHealthService(MentalHealthServiceDTO mentalHealthServiceDTO){
        MentalHealthService mentalHealthService = modelMapper.map(mentalHealthServiceDTO, MentalHealthService.class);
        mentalHealthService.setInstitutionLocation(institutionLocationRepository.findById(mentalHealthServiceDTO.getInstitutionLocationId()).get());
        return modelMapper.map(mentalHealthServiceRepository.save(mentalHealthService), MentalHealthServiceDTO.class);
    }

    public List<MentalHealthServiceDTO> getMentalHealthServicesForInstitutionLocation(long institutionLocationId){
        InstitutionLocation institutionLocation = institutionLocationRepository.findById(institutionLocationId).get();
        List<MentalHealthServiceDTO> mentalHealthServiceDTOS = new ArrayList<>();
        mentalHealthServiceRepository.findAllByInstitutionLocation(institutionLocation).forEach(mhs -> mentalHealthServiceDTOS.add(modelMapper.map(mhs, MentalHealthServiceDTO.class)));
        return mentalHealthServiceDTOS;
    }

    public InstitutionDTO createInstitution(InstitutionDTO institutionDTO){
        Institution institution = modelMapper.map(institutionDTO, Institution.class);
        return modelMapper.map(institutionRepository.save(institution), InstitutionDTO.class);
    }

    public InstitutionDTO getInstitution(long institutionId){
        Institution institution = institutionRepository.findById(institutionId).get();
        return modelMapper.map(institution, InstitutionDTO.class);
    }

    public InstitutionLocationDTO createInstitutionLocation(InstitutionLocationDTO institutionLocationDTO){
        InstitutionLocation institutionLocation = modelMapper.map(institutionLocationDTO, InstitutionLocation.class);
        institutionLocation.setInstitution(institutionRepository.findById(institutionLocationDTO.getInstitutionId()).get());
        return modelMapper.map(institutionLocationRepository.save(institutionLocation), InstitutionLocationDTO.class);
    }

    public List<InstitutionLocationDTO> getAllInstitutionLocations(){
        List<InstitutionLocationDTO> institutionLocationDTOList = new ArrayList<>();
        institutionLocationRepository.findAll().forEach(i -> institutionLocationDTOList.add(modelMapper.map(i, InstitutionLocationDTO.class)));
        return institutionLocationDTOList;
    }

    public List<SocialLocationDTO> getSocialLocationForSafetyPlan(long safetyplanId){
        List<SocialLocation> socialLocations = socialLocationRepository.findAllBySafetyplan(safetyPlanRepository.findById(safetyplanId).get());
        List<SocialLocationDTO> socialLocationDTOS = new ArrayList<>();
        socialLocations.forEach(sl -> socialLocationDTOS.add(modelMapper.map(sl, SocialLocationDTO.class)));
        return socialLocationDTOS;
    }

    public SocialLocationDTO createSocialLocation(SocialLocationDTO socialLocationDTO){
        SocialLocation socialLocation = modelMapper.map(socialLocationDTO, SocialLocation.class);
        return modelMapper.map(socialLocationRepository.save(socialLocation), SocialLocationDTO.class);
    }
}
