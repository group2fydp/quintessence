package com.cove.user.services;

import com.cove.user.dto.model.ClinicianDTO;
import com.cove.user.dto.model.StudentDTO;
import com.cove.user.model.entities.*;
import com.cove.user.repository.*;
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
public class StudentServiceImpl extends TenantService implements StudentService {
    @Autowired
    private JpaStudentRepository studentRepository;

    @Autowired
    private JpaClinicianRepository clinicianRepository;

    @Autowired
    private JpaProgramRepository programRepository;

    @Autowired
    private JpaSchoolRepository schoolRepository;

    @Autowired
    private JpaFacultyRepository facultyRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private ModelMapper modelMapper;


    public List<StudentDTO> getAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        if (!studentList.isEmpty()){
            studentList.forEach(student -> studentDTOS.add(mapEntityToDto(student)));

        }
        return studentDTOS;
    }


    public StudentDTO getStudentById(long studentId){
        //TODO: modify Optional student object for null objects
        if (studentRepository.findById(studentId).isPresent()){
            Student student = studentRepository.findById(studentId).get();
            StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
            studentDTO.setClinicianId(student.getClinician().getClinicianId());
            studentDTO.setFacultyName(student.getProgram().getFaculty().getName());
            studentDTO.setSchoolName(student.getProgram().getFaculty().getSchool().getName());
            return studentDTO;
        }
        throw new EntityNotFoundException("Student not found " + studentId);
    }

    public StudentDTO getStudentByUsername(String username){
        Student student = studentRepository.findByUsername(username);
        if(student != null){
            Program program = programRepository.findById(student.getProgram().getProgramId()).get();
            Faculty faculty = facultyRepository.findById(program.getFaculty().getFacultyId()).get();
            School school = schoolRepository.findById(faculty.getSchool().getSchoolId()).get();
            StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
            studentDTO.setClinicianId(student.getClinician().getClinicianId());
            studentDTO.setProgramName(program.getName());
            studentDTO.setFacultyName(faculty.getName());
            studentDTO.setSchoolName(school.getName());
            return studentDTO;
        }

        throw new EntityNotFoundException("Student not found " + username);
    }

    public void addStudent(StudentDTO studentDTO){
        studentDTO.setPassword(encoder.encode(String.valueOf(studentDTO.getPassword())));
        Student student = modelMapper.map(studentDTO, Student.class);
        student.setClinician(clinicianRepository.findById(studentDTO.getClinicianId()).get());
        studentRepository.save(student);
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
                student.get().setClinician(clinician);
                return modelMapper.map(studentRepository.save(student.get()), StudentDTO.class);
            }
        }
        throw new EntityNotFoundException("Student not found " + studentId);
    }


    public String deleteStudent(long studentId){
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()){
            studentRepository.delete(student.get());
            return "SUCCESS";
        }
        else {
            throw new ResourceNotFoundException();
        }
    }

    private StudentDTO mapEntityToDto(Student student){
        StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
        studentDTO.setClinicianId(student.getClinician().getClinicianId());
        studentDTO.setFacultyName(student.getProgram().getFaculty().getName());
        studentDTO.setSchoolName(student.getProgram().getFaculty().getSchool().getName());
        studentDTO.setSafetyplanId(student.getSafetyplanId());
        return studentDTO;
    }
}
