package com.cove.user.services;

import com.cove.user.dto.model.ClinicianDTO;
import com.cove.user.dto.model.StudentDTO;
import com.cove.user.model.entities.Clinician;
import com.cove.user.model.entities.Student;
import com.cove.user.repository.JpaClinicianRepository;
import com.cove.user.repository.JpaStudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClinicianServiceImpl extends TenantService implements ClinicianService {

    @Autowired
    private JpaClinicianRepository clinicianRepository;

    @Autowired
    private JpaStudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public ClinicianDTO getClinicianById(long clinicianId){
        Optional<Clinician> clinician = clinicianRepository.findById(clinicianId);
        if (clinician.isPresent()){
            return modelMapper.map(clinician.get(), ClinicianDTO.class);
        }
        else {
            throw new EntityNotFoundException("Clinician not found " + clinicianId);
        }
    }

    public Optional<ClinicianDTO> getClinicianByUsername(String username){
        Optional<Clinician> clinician = clinicianRepository.findByUsername(username);
        if(clinician.isPresent()){
            return Optional.of(modelMapper.map(clinician.get(), ClinicianDTO.class));
        }
        return Optional.empty();
    }

    public ClinicianDTO updateClinician(ClinicianDTO clinicianDTO) {
        Optional<Clinician> clinician = clinicianRepository.findById(clinicianDTO.getClinicianId());
        if (clinician.isPresent()){
            Clinician clinicianModel = clinician.get();
            clinicianModel.setFirstName(clinicianDTO.getFirstName());
            clinicianModel.setLastName(clinicianDTO.getLastName());
            clinicianModel.setPreferredName(clinicianDTO.getPreferredName());
            clinicianModel.setEmail(clinicianDTO.getEmail());
            clinicianModel.setPhone(clinicianDTO.getPhone());
            return modelMapper.map(clinicianRepository.save(clinicianModel), ClinicianDTO.class);
        }
        else {
            throw new EntityNotFoundException("Clinician not found " + clinicianDTO.getClinicianId());
        }
    }

    public ClinicianDTO addClinician(ClinicianDTO clinicianDTO){
        clinicianDTO.setPassword(encoder.encode(String.valueOf(clinicianDTO.getPassword())));
        Clinician clinician = modelMapper.map(clinicianDTO, Clinician.class);
        return modelMapper.map(clinicianRepository.save(clinician), ClinicianDTO.class);
    }

    public List<StudentDTO> getAllStudentsForClinician(long clinicianId){
        //TODO handle null optional for clinician
        Clinician clinician = clinicianRepository.findById(clinicianId).get();
        List<Student> studentList = studentRepository.findByClinician(clinician);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        studentList.forEach(student -> studentDTOS.add(mapDtoToEntity(student)));
        return studentDTOS;
    }

    private StudentDTO mapDtoToEntity(Student student){
        StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
        studentDTO.setClinicianId(student.getClinician().getClinicianId());
        studentDTO.setProgramName(student.getProgram().getName());
        studentDTO.setFacultyName(student.getProgram().getFaculty().getName());
        studentDTO.setSchoolName(student.getProgram().getFaculty().getSchool().getName());
        return studentDTO;
    }

    public void deleteClinician(long clinicianId){
        Optional<Clinician> clinician = clinicianRepository.findById(clinicianId);
        if (clinician.isPresent()){
            clinicianRepository.delete(clinician.get());
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
