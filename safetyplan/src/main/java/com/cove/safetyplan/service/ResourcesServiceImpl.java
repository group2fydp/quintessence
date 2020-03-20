package com.cove.safetyplan.service;

import com.cove.safetyplan.dto.model.*;
import com.cove.safetyplan.model.entities.*;
import com.cove.safetyplan.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResourcesServiceImpl implements ResourcesService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JPACopingStrategyRepository copingStrategyRepository;

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


    @Override
    public List<HelplineDTO> getHelplines(){
        List<HelplineDTO> helplineDTOS = new ArrayList<>();
        helplineRepository.findAll().forEach(h -> helplineDTOS.add(modelMapper.map(h, HelplineDTO.class)));
        return helplineDTOS;
    }

    @Override
    public HelplineDTO createHelpline(HelplineDTO helplineDTO){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Helpline helpline = modelMapper.map(helplineDTO, Helpline.class);
        return modelMapper.map(helplineRepository.save(helpline), HelplineDTO.class);
    }

    @Override
    public MentalHealthServiceDTO createMentalHealthService(MentalHealthServiceDTO mentalHealthServiceDTO){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        MentalHealthService mentalHealthService = modelMapper.map(mentalHealthServiceDTO, MentalHealthService.class);
        mentalHealthService.setInstitutionLocation(institutionLocationRepository.findById(mentalHealthServiceDTO.getInstitutionLocationId()).get());
        MentalHealthServiceDTO response = modelMapper.map(mentalHealthServiceRepository.save(mentalHealthService), MentalHealthServiceDTO.class);
        response.setInstitutionLocationId(mentalHealthService.getInstitutionLocation().getInstitutionLocationId());
        return response;
    }

    @Override
    public List<MentalHealthServiceDTO> getMentalHealthServicesForInstitutionLocation(long institutionLocationId){
        InstitutionLocation institutionLocation = institutionLocationRepository.findById(institutionLocationId).get();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        List<MentalHealthServiceDTO> mentalHealthServiceDTOS = new ArrayList<>();
        mentalHealthServiceRepository.findAllByInstitutionLocation(institutionLocation).forEach(mhs -> mentalHealthServiceDTOS.add(modelMapper.map(mhs, MentalHealthServiceDTO.class)));
        return mentalHealthServiceDTOS;
    }

    @Override
    public InstitutionDTO createInstitution(InstitutionDTO institutionDTO){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Institution institution = modelMapper.map(institutionDTO, Institution.class);
        return modelMapper.map(institutionRepository.save(institution), InstitutionDTO.class);
    }

    @Override
    public InstitutionDTO getInstitution(long institutionId){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Institution institution = institutionRepository.findById(institutionId).get();
        return modelMapper.map(institution, InstitutionDTO.class);
    }

    public InstitutionLocationResponseDTO createInstitutionLocation(InstitutionLocationRequestDTO institutionLocationRequestDTO){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        InstitutionLocation institutionLocation = modelMapper.map(institutionLocationRequestDTO, InstitutionLocation.class);
        institutionLocation.setInstitution(institutionRepository.findById(institutionLocationRequestDTO.getInstitutionId()).get());
        InstitutionLocationResponseDTO institutionLocationResponseDTO = modelMapper.map(institutionLocationRepository.save(institutionLocation), InstitutionLocationResponseDTO.class);
        institutionLocationResponseDTO.setInstitutionId(institutionLocation.getInstitution().getInstitutionId());
        return institutionLocationResponseDTO;
    }

    private List<String> availableServicesListForInstitutionLocation(long institutionLocationId){
        InstitutionLocation institutionLocation = institutionLocationRepository.findByInstitutionLocationId(institutionLocationId).get();
        List<MentalHealthService> services = mentalHealthServiceRepository.findAllByInstitutionLocation(institutionLocation);
        List<String> serviceNames = new ArrayList<>();
        services.forEach(s -> serviceNames.add(s.getName()));
        return serviceNames;

    }

    @Override
    public List<InstitutionLocationResponseDTO> getAllInstitutionLocations(){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        List<InstitutionLocationResponseDTO> institutionLocationResponseDTOList = new ArrayList<>();
        for (InstitutionLocation institutionLocation : institutionLocationRepository.findAll()){
            InstitutionLocationResponseDTO responseDTO = modelMapper.map(institutionLocation, InstitutionLocationResponseDTO.class);
            responseDTO.setInstitutionId(institutionLocation.getInstitution().getInstitutionId());
            institutionLocationResponseDTOList.add(responseDTO);
        }
        institutionLocationResponseDTOList.forEach(dto -> dto.setServices(availableServicesListForInstitutionLocation(dto.getInstitutionLocationId())));
        return institutionLocationResponseDTOList;
    }

    @Override
    public List<SocialLocationDTO> getSocialLocationForSafetyPlan(long safetyplanId){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        List<SocialLocation> socialLocations = socialLocationRepository.findAllBySafetyplan(safetyPlanRepository.findById(safetyplanId).get());
        List<SocialLocationDTO> socialLocationDTOS = new ArrayList<>();
        socialLocations.forEach(sl -> socialLocationDTOS.add(modelMapper.map(sl, SocialLocationDTO.class)));
        return socialLocationDTOS;
    }

    @Override
    public SocialLocationDTO createSocialLocation(SocialLocationDTO socialLocationDTO){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        SocialLocation socialLocation = modelMapper.map(socialLocationDTO, SocialLocation.class);
        SocialLocationDTO response = modelMapper.map(socialLocationRepository.save(socialLocation), SocialLocationDTO.class);
        response.setSafetyPlanId(socialLocation.getSafetyplan().getSafetyplanId());
        return response;
    }
}
