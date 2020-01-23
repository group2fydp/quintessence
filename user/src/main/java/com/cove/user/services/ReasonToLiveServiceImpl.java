package com.cove.user.services;

import com.cove.user.dto.model.ReasonToLiveDTO;
import com.cove.user.model.entities.ReasonToLive;
import com.cove.user.model.entities.Student;
import com.cove.user.repository.JpaReasonToLiveRepository;
import com.cove.user.repository.JpaStudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReasonToLiveServiceImpl implements ReasonToLiveService {
    @Autowired
    private JpaStudentRepository studentRepository;

    @Autowired
    private JpaReasonToLiveRepository reasonToLiveRepository;

    @Autowired(required = false)
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
        return modelMapper.map(reasonToLiveRepository.save(reasonToLive), ReasonToLiveDTO.class)
                .setStudentId(reasonToLive.getStudent().getStudentId());
    }

    public ReasonToLiveDTO updateReasonToLive(ReasonToLiveDTO reasonToLiveDTO){
        Optional<ReasonToLive> reasonToLive = reasonToLiveRepository.findById(reasonToLiveDTO.getReasonToLiveId());
        if (reasonToLive.isPresent()){
            ReasonToLive reasonToLiveModel = reasonToLive.get();
            reasonToLiveModel.setTitle(reasonToLiveDTO.getTitle());
            return modelMapper.map(reasonToLiveRepository.save(reasonToLiveModel), ReasonToLiveDTO.class)
                    .setStudentId(reasonToLiveModel.getStudent().getStudentId());
        }
        throw new EntityNotFoundException("ReasonToLive entity not found" + reasonToLiveDTO.getReasonToLiveId());
    }

    public void deleteReasonToLive(long reasonToLiveId){
        Optional<ReasonToLive> reasonToLive = reasonToLiveRepository.findById(reasonToLiveId);
        if (reasonToLive.isPresent()){
            reasonToLiveRepository.delete(reasonToLive.get());
        }
        else {
            throw new ResourceNotFoundException();
        }
    }

}
