package com.cove.user.services;

import com.cove.user.dto.model.ClinicianDTO;
import com.cove.user.dto.model.StudentDTO;
import com.cove.user.model.entities.Clinician;
import com.cove.user.model.entities.Student;
import com.cove.user.repository.JpaClinicianRepository;
import com.cove.user.repository.JpaStudentRepository;
import com.cove.user.security.StudentPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl extends TenantService implements StudentService, UserDetailsService {
    @Autowired
    private JpaStudentRepository studentRepository;

    @Autowired
    private JpaClinicianRepository clinicianRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private ModelMapper modelMapper;

    public List<StudentDTO> getAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        if (!studentList.isEmpty()){
            studentList.forEach(student -> studentDTOS.add(modelMapper.map(student, StudentDTO.class)));

        }
        return studentDTOS;
    }


    public StudentDTO getStudentById(long studentId){
        //TODO: modify Optional student object for null objects
        if (studentRepository.findById(studentId).isPresent()){
            Student student = studentRepository.findById(studentId).get();
            return modelMapper.map(student, StudentDTO.class);
        }
        throw new EntityNotFoundException("Student not found " + studentId);
    }

    public StudentDTO addStudent(StudentDTO studentDTO){
        studentDTO.setPassword(encoder.encode(studentDTO.getPassword()));
        Student student = modelMapper.map(studentDTO, Student.class);
        student.setClinician(clinicianRepository.findById(studentDTO.getClinicianId()).get());
        return modelMapper.map(studentRepository.save(student), StudentDTO.class)
                .setClinicianId(student.getClinician().getClinicianId());
    }

    public StudentDTO updateStudent(StudentDTO studentDTO) {
        Optional<Student> student = studentRepository.findById(studentDTO.getStudentId());
        if (student.isPresent()){
            Student studentModel = student.get();
            studentModel.setSafetyplanId(studentDTO.getSafetyplanId());
            studentModel.setGender(studentDTO.getGender());
            if (clinicianRepository.findById(studentDTO.getClinicianId()).isPresent()){
                Clinician clinician = clinicianRepository.findById(studentDTO.getClinicianId()).get();
                studentModel.setClinician(clinician);
            }
            studentModel.setFirstName(studentDTO.getFirstName());
            studentModel.setLastName(studentDTO.getLastName());
            studentModel.setPreferredName(studentDTO.getPreferredName());
            studentModel.setStudentEmail(studentDTO.getStudentEmail());
            studentModel.setPersonalEmail(studentDTO.getPersonalEmail());
            studentModel.setCellPhone(studentDTO.getCellPhone());
            studentModel.setHomePhone(studentDTO.getHomePhone());
            return modelMapper.map(studentRepository.save(studentModel), StudentDTO.class);
        }
        throw new EntityNotFoundException("Student not found " + studentDTO.getStudentId());
    }

    public StudentDTO assignClinianByStudentId(long studentId, ClinicianDTO clinicianDTO) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()){
            if (clinicianRepository.findById(clinicianDTO.getClinicianId()).isPresent()){
                Clinician clinician = clinicianRepository.findById(clinicianDTO.getClinicianId()).get();
                return modelMapper.map(studentRepository.save(student.get()), StudentDTO.class);
            }
        }
        throw new EntityNotFoundException("Student not found " + studentId);
    }


    public void deleteStudent(long studentId){
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()){
            studentRepository.delete(student.get());
        }
        else {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        Student student = studentRepository.findByUsername(username);
        if (student == null){
            throw new UsernameNotFoundException(username);
        }

        return new StudentPrincipal(student);
    }
}
