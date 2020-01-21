package com.cove.user.services;

import com.cove.user.dto.model.WarningSignDTO;
import com.cove.user.model.entities.Student;
import com.cove.user.model.entities.WarningSign;
import com.cove.user.repository.JpaStudentRepository;
import com.cove.user.repository.JpaWarningSignRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarningSignServiceImpl implements WarningSignService {
    @Autowired
    private JpaStudentRepository studentRepository;

    @Autowired
    private JpaWarningSignRepository warningSignRepository;


    @Autowired(required = false)
    private ModelMapper modelMapper;

    public List<WarningSignDTO> getAllWarningSignsForStudent(long studentId){
        Optional<Student> student = studentRepository.findById(studentId);
        List<WarningSignDTO> warningSigns = new ArrayList<>();
        if (student.isPresent()){
            warningSignRepository.findAllByStudent(student.get())
                    .forEach(warningSign ->
                            warningSigns.add(modelMapper.map(warningSign, WarningSignDTO.class)));
        }
        return warningSigns;
    }

    public WarningSignDTO addWarningSign(WarningSignDTO warningSignDTO){
        WarningSign warningSign = modelMapper.map(warningSignDTO, WarningSign.class);
        warningSign.setStudent(studentRepository.findById(warningSignDTO.getStudentId()).get());
        return modelMapper.map(warningSignRepository.save(warningSign), WarningSignDTO.class)
                .setStudentId(warningSign.getStudent().getStudentId());
    }

    public WarningSignDTO updateWarningSign(WarningSignDTO warningSignDTO) {
        Optional<WarningSign> warningSign = warningSignRepository.findById(warningSignDTO.getWarningSignId());
        if (warningSign.isPresent()){
            WarningSign warningSignModel = warningSign.get();
            warningSignModel.setCategory(warningSignDTO.getCategory());
            warningSignModel.setSeverity(warningSignDTO.getSeverity());
            warningSignModel.setTitle(warningSignDTO.getTitle());
            return modelMapper.map(warningSignRepository.save(warningSignModel), WarningSignDTO.class)
                    .setStudentId(warningSignModel.getStudent().getStudentId());
        }
        throw new EntityNotFoundException("Warning sign not found " + warningSignDTO.getWarningSignId());
    }

    public void deleteWarningSign(long warningSignId){
        Optional<WarningSign> warningSign = warningSignRepository.findById(warningSignId);
        if (warningSign.isPresent()){
            warningSignRepository.delete(warningSign.get());
        }
        else {
            throw new ResourceNotFoundException();
        }
    }
}
