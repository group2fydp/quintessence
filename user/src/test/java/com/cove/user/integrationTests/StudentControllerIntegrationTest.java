package com.cove.user.integrationTests;

import com.cove.safetyplan.util.DateUtils;
import com.cove.user.controllers.StudentController;
import com.cove.user.dto.model.StudentDTO;
import com.cove.user.model.entities.*;
import com.cove.user.repository.JpaClinicianRepository;
import com.cove.user.repository.JpaProgramRepository;
import com.cove.user.repository.JpaStudentRepository;
import com.cove.user.services.StudentServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StudentControllerIntegrationTest {
//    @Autowired
//    private MockMvc mockMvc;

//    @MockBean
//    private ModelMapper modelMapper;
//
//    @MockBean
//    private JpaStudentRepository studentRepository;
//
//    @MockBean
//    private StudentServiceImpl studentService;

    @Autowired
    private JpaClinicianRepository clinicianRepository;

    @Autowired
    private JpaProgramRepository programRepository;

    @Autowired
    private StudentController studentController;

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
    public void init() {
        initMocks(this);
        studentDTO1.setClinicianId(10000000L);
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
        studentDTO1.setProgramId(1L);

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

//        clinician1.setClinicianId(1L);
//        clinician1.setTenantId(1L);
//        clinician1.setUsername("janeDoe");
//        clinician1.setFirstName("Jane");
//        clinician1.setLastName("Doe");
//        clinician1.setPhone("2222222");
//        clinician1.setEmail("test1@test.com");
//        clinician1.setPreferredName("A");
//        clinician1.setRole(1);
//        clinician1.setLastModifyDate(DateUtils.today());
//        clinician1.setCreateDate(DateUtils.today());
//
//        clinician2.setClinicianId(2L);
//        clinician2.setTenantId(1L);
//        clinician2.setUsername("joeDoe");
//        clinician2.setFirstName("Joe");
//        clinician2.setLastName("Doe");
//        clinician2.setPhone("3333333");
//        clinician2.setEmail("jDoe@test.com");
//        clinician2.setPreferredName("B");
//        clinician2.setRole(1);
//        clinician2.setLastModifyDate(DateUtils.today());
//        clinician2.setCreateDate(DateUtils.today());

        student1.setStudentId(1L);
        student1.setPassword("password");
        student1.setUsername("test1");
        student1.setFirstName("Jane");
        student1.setLastName("Doe");
        student1.setSafetyplanId(1L);
        student1.setPreferredName("A");
        student1.setCellPhone("1111111");
        student1.setStudentEmail("test@test.ca");
        student1.setStudentNumber(111L);
        student1.setCreateDate(DateUtils.today());
        student1.setLastModifyDate(DateUtils.today());

//        school.setName("Test");
//        school.setSchoolId(1L);
//
//        faculty.setFacultyId(1L);
//        faculty.setSchool(school);
//        faculty.setName("Test");
//
//        program.setProgramId(1L);
//        program.setFaculty(faculty);
//        program.setName("test");

        program = programRepository.findById(1L).get();
        clinician1 = clinicianRepository.findById(10000000L).get();

        student1.setClinician(clinician1);
        student2.setClinician(clinician1);
        student1.setProgram(program);
        student2.setProgram(program);

//        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(studentDTO1);
//        Mockito.when(studentService.getStudentById(Mockito.anyLong())).thenReturn(studentDTO1);

    }

    @Test
    public void findStudentById() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/student/1").header("X-TenantID", 1))
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.studentId", Matchers.is(1)));
//
//        Mockito.verify(studentRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void testCreateStudentAndRetrieveById(){
        studentController.createStudent(studentDTO1);
        StudentDTO result = studentController.getStudent(1L);
        Assertions.assertEquals(studentDTO1, result);
    }

}
