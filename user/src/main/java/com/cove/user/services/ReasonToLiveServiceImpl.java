package com.cove.user.services;

import com.cove.user.dto.model.ReasonToLiveDTO;
import com.cove.user.model.entities.ReasonToLive;
import com.cove.user.model.entities.Student;
import com.cove.user.repository.JpaStudentEntityRepository;
import com.cove.user.repository.JpaStudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReasonToLiveServiceImpl implements ReasonToLiveService {
    @Autowired
    private JpaStudentRepository studentRepository;

    @Autowired
    private JpaStudentEntityRepository<ReasonToLive> reasonToLiveRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ReasonToLiveDTO> getAllReasonsToLiveForStudent(long studentId){
        Optional<Student> student = studentRepository.findById(studentId);
        List<ReasonToLiveDTO> reasonToLiveList = new ArrayList<>();
        if (student.isPresent()){
            reasonToLiveRepository.findAllByStudent(student.get())
                    .forEach(r -> reasonToLiveList.add(modelMapper.map(r, ReasonToLiveDTO.class)));
        }
        return reasonToLiveList;
    }

    public ReasonToLiveDTO addReasonToLive(ReasonToLiveDTO reasonToLiveDTO){
        ReasonToLive reasonToLive = modelMapper.map(reasonToLiveDTO, ReasonToLive.class);
        reasonToLive.setStudent(studentRepository.findById(reasonToLiveDTO.getStudentId()).get());
        return modelMapper.map(reasonToLiveRepository.save(reasonToLive), ReasonToLiveDTO.class);
    }

    public ReasonToLiveDTO updateReasonToLive(ReasonToLiveDTO reasonToLiveDTO){
        Optional<ReasonToLive> reasonToLive = reasonToLiveRepository.findById(reasonToLiveDTO.getReasonToLiveId());
        if (reasonToLive.isPresent()){
            ReasonToLive reasonToLiveModel = reasonToLive.get();
            reasonToLiveModel.setTitle(reasonToLiveDTO.getTitle());
            return modelMapper.map(reasonToLiveRepository.save(reasonToLiveModel), ReasonToLiveDTO.class);
        }
        throw new EntityNotFoundException("ReasonToLive entity not found" + reasonToLiveDTO.getReasonToLiveId());
    }

    public void deleteReasonToLive(long reasonToLiveId){
        Optional<ReasonToLive> reasonToLive = reasonToLiveRepository.findById(reasonToLiveId);
        if (reasonToLive.isPresent()){
            reasonToLiveRepository.delete(reasonToLive.get());
        }
        throw new ResourceNotFoundException();
    }

}
