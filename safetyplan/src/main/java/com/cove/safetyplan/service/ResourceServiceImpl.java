package com.cove.safetyplan.service;

import com.cove.safetyplan.dto.model.HelplineDTO;
import com.cove.safetyplan.model.entities.Helpline;
import com.cove.safetyplan.repository.JpaHelplineRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ResourcesServiceImpl implements ResourcesService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JpaHelplineRepository helplineRepository;

    public List<HelplineDTO> getHelplines(){
        List<HelplineDTO> helplineDTOS = new ArrayList<>();
        helplineRepository.findAll().forEach(h -> helplineDTOS.add(modelMapper.map(h, HelplineDTO.class)));
        return helplineDTOS;
    }

    public HelplineDTO createHelpline(HelplineDTO helplineDTO){

        return modelMapper.map(helplineRepository.save());
    }
}
