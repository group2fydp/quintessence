package com.cove.user.services;

import com.cove.user.annotations.ReadsTenantData;
import com.cove.user.dto.model.ClinicianDTO;
import com.cove.user.dto.model.StudentDTO;
import com.cove.user.exception.UserNotFoundException;
import com.cove.user.model.entities.Clinician;
import com.cove.user.model.entities.Student;
import com.cove.user.repository.JpaClinicianRepository;
import com.cove.user.repository.JpaStudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl extends TenantService implements StudentService {
    @Autowired
    private JpaStudentRepository studentRepository;

    @PersistenceContext
    public EntityManager entityManager;

    @Autowired
    private JpaClinicianRepository clinicianRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private ModelMapper modelMapper;

    @ReadsTenantData
    public List<StudentDTO> getAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        studentList.forEach(student -> studentDTOS.add(modelMapper.map(student, StudentDTO.class)));
        return studentDTOS;
    }

    @ReadsTenantData
    public List<StudentDTO> getAllStudentsForClinician(long clinicianId){
        //TODO handle null optional for clinician
        Clinician clinician = clinicianRepository.findById(clinicianId).get();
        List<Student> studentList = studentRepository.findByClinician(clinician);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        studentList.forEach(student -> studentDTOS.add(modelMapper.map(student, StudentDTO.class)));
        return studentDTOS;
    }

    @ReadsTenantData
    public StudentDTO getStudentById(long studentId){
        //TODO: modify Optional student object for null objects
        Student student = studentRepository.findById(studentId).get();
        return modelMapper.map(student, StudentDTO.class);
    }

    @ReadsTenantData
    public StudentDTO addStudent(StudentDTO studentDTO){
        studentDTO.setPassword(encoder.encode(studentDTO.getPassword()));
        Student student = modelMapper.map(studentDTO, Student.class);
        student.setClinician(clinicianRepository.findById(studentDTO.getClinicianId()).get());
        return modelMapper.map(studentRepository.save(student), StudentDTO.class);
    }

    @ReadsTenantData
    public StudentDTO updateStudentProfile(StudentDTO studentDTO) throws UserNotFoundException{
        Optional<Student> student = Optional.of(studentRepository.findByStudentNumber(studentDTO.getStudentNumber()));
        if (student.isPresent()){
            Student studentModel = student.get();
            studentModel.setFirstName(studentDTO.getFirstName());
            studentModel.setLastName(studentDTO.getLastName());
            studentModel.setPreferredName(studentDTO.getPreferredName());
            studentModel.setStudentEmail(studentDTO.getStudentEmail());
            studentModel.setPersonalEmail(studentDTO.getPersonalEmail());
            studentModel.setCellPhone(studentDTO.getCellPhone());
            studentModel.setHomePhone(studentDTO.getHomePhone());
            return modelMapper.map(studentRepository.save(studentModel), StudentDTO.class);
        }
        throw new UserNotFoundException("Student not found " + studentDTO.getStudentId());
    }

    @ReadsTenantData
    public StudentDTO assignClinianByStudentId(long studentId, ClinicianDTO clinicianDTO) throws UserNotFoundException{
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()){
            //TODO add logic for clinician assignment
            return modelMapper.map(studentRepository.save(student.get()), StudentDTO.class);
        }
        throw new UserNotFoundException("Student not found " + studentId);
    }
}
