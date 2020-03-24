package com.cove.user.integrationTests;

import com.cove.user.dto.model.StudentDTO;
import com.cove.user.services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.swing.text.html.Option;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentControllerIntegrationTest {
    @MockBean
    private StudentService studentService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeAll
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testGetStudentById() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(1);
        studentDTO.setSafetyplanId(2);
        studentDTO.setTenantId(1);
        studentDTO.setProgramId(1);
        studentDTO.setFirstName("Test");
        studentDTO.setLastName("Student");
        studentDTO.setCellPhone("11111111");
        studentDTO.setStudentEmail("test@test.com");
        studentDTO.setStudentNumber(1111111);
        studentDTO.setPassword("password");
        studentDTO.setClinicianId(1);
        studentDTO.setUsername("testStudent");

        Mockito.when(studentService.getStudentById(Mockito.anyLong())).thenReturn(studentDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/{studentId}", 1)
            .header("X-TenantID", 1)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.studentId").value(1));
    }

    @Test
    public void testCreateStudent() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(1);
        studentDTO.setSafetyplanId(2);
        studentDTO.setTenantId(1);
        studentDTO.setProgramId(1);
        studentDTO.setFirstName("Test");
        studentDTO.setLastName("Student");
        studentDTO.setCellPhone("11111111");
        studentDTO.setStudentEmail("test@test.com");
        studentDTO.setStudentNumber(1111111);
        studentDTO.setPassword("password");
        studentDTO.setClinicianId(1);
        studentDTO.setUsername("testStudent");

        String requestBody = objectMapper.writeValueAsString(studentDTO);

        Mockito.when(studentService.addStudentAndGetStudentByUsername(Mockito.any())).thenReturn(studentDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/student/new")
                .header("X-TenantID", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentId").value(1));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(1);
        studentDTO.setSafetyplanId(2);
        studentDTO.setTenantId(1);
        studentDTO.setProgramId(1);
        studentDTO.setFirstName("Test");
        studentDTO.setLastName("Student");
        studentDTO.setCellPhone("11111111");
        studentDTO.setStudentEmail("test@test.com");
        studentDTO.setStudentNumber(1111111);
        studentDTO.setPassword("password");
        studentDTO.setClinicianId(1);
        studentDTO.setUsername("testStudent");

        String requestBody = objectMapper.writeValueAsString(studentDTO);

        Mockito.when(studentService.updateStudent(Mockito.any())).thenReturn(studentDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/student/update")
                .header("X-TenantID", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentId").value(1));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/student/delete/{id}", 1).header("X-TenantID", 1).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.deleted").value(true));
    }
}
