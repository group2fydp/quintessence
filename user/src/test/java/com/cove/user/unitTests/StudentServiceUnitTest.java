package com.cove.user.unitTests;

import com.cove.safetyplan.util.DateUtils;
import com.cove.user.dto.model.ClinicianDTO;
import com.cove.user.dto.model.StudentDTO;
import com.cove.user.model.entities.*;
import com.cove.user.repository.*;
import com.cove.user.services.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.MockitoAnnotations.initMocks;

public class StudentServiceUnitTest {
    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private BCryptPasswordEncoder encoder;

    @Mock
    private JpaStudentRepository studentRepository;

    @Mock
    private JpaClinicianRepository clinicianRepository;

    @Mock
    private JpaProgramRepository programRepository;

    @Mock
    private JpaFacultyRepository facultyRepository;

    @Mock
    private JpaSchoolRepository schoolRepository;

    private Student student1 = new Student();
    private Student student2 = new Student();
    private StudentDTO studentDTO1 = new StudentDTO();
    private StudentDTO studentDTO2 = new StudentDTO();
    private Clinician clinician1 = new Clinician();
    private Clinician clinician2 = new Clinician();
    private Program program = new Program();
    private Faculty faculty = new Faculty();
    private School school = new School();

    @BeforeEach
    public void setup() throws Exception {
        initMocks(this);

        studentDTO1.setClinicianId(1L);
        studentDTO1.setTenantId(1L);
        studentDTO1.setPassword("password");
        studentDTO1.setUsername("test1");
        studentDTO1.setFirstName("Jane");
        studentDTO1.setLastName("Doe");
        studentDTO1.setSafetyplanId(1L);
        studentDTO1.setPreferredName("A");
        studentDTO1.setCellPhone("1111111");
        studentDTO1.setStudentEmail("test@test.ca");
        studentDTO1.setStudentNumber(111L);

        studentDTO2.setClinicianId(1L);
        studentDTO2.setTenantId(1L);
        studentDTO2.setPassword("password");
        studentDTO2.setUsername("test2");
        studentDTO2.setFirstName("John");
        studentDTO2.setLastName("Doe");
        studentDTO2.setSafetyplanId(2L);
        studentDTO2.setPreferredName("B");
        studentDTO2.setCellPhone("2222222");
        studentDTO2.setStudentEmail("test1@test.ca");
        studentDTO2.setStudentNumber(222L);

        clinician1.setClinicianId(1L);
        clinician1.setTenantId(1L);
        clinician1.setUsername("janeDoe");
        clinician1.setFirstName("Jane");
        clinician1.setLastName("Doe");
        clinician1.setPhone("2222222");
        clinician1.setEmail("test1@test.com");
        clinician1.setPreferredName("A");
        clinician1.setRole(1);
        clinician1.setLastModifyDate(DateUtils.today());
        clinician1.setCreateDate(DateUtils.today());

        clinician2.setClinicianId(2L);
        clinician2.setTenantId(1L);
        clinician2.setUsername("joeDoe");
        clinician2.setFirstName("Joe");
        clinician2.setLastName("Doe");
        clinician2.setPhone("3333333");
        clinician2.setEmail("jDoe@test.com");
        clinician2.setPreferredName("B");
        clinician2.setRole(1);
        clinician2.setLastModifyDate(DateUtils.today());
        clinician2.setCreateDate(DateUtils.today());

        school.setName("Test");
        school.setSchoolId(1L);

        faculty.setFacultyId(1L);
        faculty.setSchool(school);
        faculty.setName("Test");

        program.setProgramId(1L);
        program.setFaculty(faculty);
        program.setName("test");

        student1.setClinician(clinician1);
        student2.setClinician(clinician1);
        student1.setProgram(program);
        student2.setProgram(program);
    }

    @Test
    public void testGetStudentById(){
        Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(student1));
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(studentDTO1);
        StudentDTO result = studentService.getStudentById(1L);
        Assertions.assertEquals(studentDTO1, result);
    }

    @Test
    public void testGetStudentByUsername(){
        Mockito.when(programRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(program));
        Mockito.when(facultyRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(faculty));
        Mockito.when(schoolRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(school));
        Mockito.when(studentRepository.findByUsername(Mockito.anyString())).thenReturn(student1);
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(studentDTO1);
        StudentDTO result = studentService.getStudentByUsername("test1");
        Assertions.assertEquals(studentDTO1, result);
    }

    @Test
    public void testAddStudent(){
        Mockito.when(studentRepository.save(Mockito.any())).thenReturn(student2);
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(student2);
        Mockito.when(clinicianRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(clinician1));
        studentService.addStudent(studentDTO2);

        Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(student2));
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(studentDTO2);
        StudentDTO result = studentService.getStudentById(2L);
        Assertions.assertEquals(studentDTO2, result);

    }

    @Test
    public void testUpdateStudent(){
        student1.setFirstName("Joe");
        student1.setLastName("Dow");
        student1.setCellPhone("333333");
        student1.setStudentEmail("test@email.ca");

        studentDTO1.setFirstName("Joe");
        studentDTO1.setLastName("Dow");
        studentDTO1.setCellPhone("333333");
        studentDTO1.setStudentEmail("test@email.ca");

        Mockito.when(studentRepository.save(Mockito.any())).thenReturn(student1);
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(studentDTO1);
        Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(student1));
        Mockito.when(clinicianRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(clinician1));

        StudentDTO result = studentService.updateStudent(studentDTO1);
        Assertions.assertEquals(studentDTO1, result);
    }

    @Test
    public void testDeleteStudent(){
        Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(student2));
        String response = studentService.deleteStudent(2L);
        Assertions.assertEquals("SUCCESS", response);
    }

    @Test
    public void testAssignClinicianToStudentById(){
        Mockito.when(studentRepository.findById(Mockito.any())).thenReturn(Optional.of(student1));
        Mockito.when(clinicianRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(clinician2));
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(studentDTO1);
        ClinicianDTO newClinician = new ClinicianDTO();
        newClinician.setTenantId(1L);
        newClinician.setUsername("joeDoe");
        newClinician.setFirstName("Joe");
        newClinician.setLastName("Doe");
        newClinician.setPhone("3333333");
        newClinician.setEmail("jDoe@test.com");
        newClinician.setPreferredName("B");
        newClinician.setRole(1);

        StudentDTO result = studentService.assignClinianByStudentId(1L, newClinician);
        Assertions.assertEquals(student1.getClinician().getClinicianId(), clinician2.getClinicianId());
    }

}
