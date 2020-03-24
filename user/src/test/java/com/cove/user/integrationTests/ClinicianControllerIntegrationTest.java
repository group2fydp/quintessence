package com.cove.user.integrationTests;

import com.cove.user.dto.model.ClinicianDTO;
import com.cove.user.dto.model.StudentDTO;
import com.cove.user.services.ClinicianService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClinicianControllerIntegrationTest {

    @MockBean
    private ClinicianService clinicianService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeAll
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testGetClinicianByUsername() throws Exception {
        ClinicianDTO clinicianDTO = new ClinicianDTO();
        clinicianDTO.setTenantId(1);
        clinicianDTO.setClinicianId(1);
        clinicianDTO.setFirstName("Test");
        clinicianDTO.setLastName("Clinician");
        clinicianDTO.setPassword("password");
        clinicianDTO.setUsername("testClinician");
        clinicianDTO.setEmail("testClinician@test.com");
        clinicianDTO.setPhone("1111111");
        clinicianDTO.setRole(1);

        Mockito.when(clinicianService.getClinicianByUsername(Mockito.anyString())).thenReturn(Optional.of(clinicianDTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/clinician/{username}", "testClinician")
            .header("X-TenantID", 1)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.clinicianId").value(1));
    }

    @Test
    public void testGetStudentByClinician() throws Exception {
        ClinicianDTO clinicianDTO = new ClinicianDTO();
        clinicianDTO.setTenantId(1);
        clinicianDTO.setClinicianId(1);
        clinicianDTO.setFirstName("Test");
        clinicianDTO.setLastName("Clinician");
        clinicianDTO.setPassword("password");
        clinicianDTO.setUsername("testClinician");
        clinicianDTO.setEmail("testClinician@test.com");
        clinicianDTO.setPhone("1111111");
        clinicianDTO.setRole(1);

        StudentDTO studentDTO1 = new StudentDTO();
        studentDTO1.setStudentId(1);
        studentDTO1.setSafetyplanId(1);
        studentDTO1.setTenantId(1);
        studentDTO1.setProgramId(1);
        studentDTO1.setFirstName("Test");
        studentDTO1.setLastName("Student 1");
        studentDTO1.setCellPhone("11111111");
        studentDTO1.setStudentEmail("test@test.com");
        studentDTO1.setStudentNumber(1111111);
        studentDTO1.setPassword("password");
        studentDTO1.setClinicianId(1);

        StudentDTO studentDTO2 = new StudentDTO();
        studentDTO2.setStudentId(1);
        studentDTO2.setSafetyplanId(1);
        studentDTO2.setTenantId(1);
        studentDTO2.setProgramId(1);
        studentDTO2.setFirstName("Test");
        studentDTO2.setLastName("Student 1");
        studentDTO2.setCellPhone("11111111");
        studentDTO2.setStudentEmail("test@test.com");
        studentDTO2.setStudentNumber(1111111);
        studentDTO2.setPassword("password");
        studentDTO2.setClinicianId(1);

        List<StudentDTO> studentList = new ArrayList<>();
        studentList.add(studentDTO1);
        studentList.add(studentDTO2);

        Mockito.when(clinicianService.getAllStudentsForClinician(Mockito.anyLong())).thenReturn(studentList);
        mockMvc.perform(MockMvcRequestBuilders.get("/clinician/{clinicianId}/students", 1)
                .header("X-TenantID", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCreateClinician() throws Exception {
        ClinicianDTO clinicianDTO = new ClinicianDTO();
        clinicianDTO.setTenantId(1);
        clinicianDTO.setClinicianId(1);
        clinicianDTO.setFirstName("Test");
        clinicianDTO.setLastName("Clinician");
        clinicianDTO.setPassword("password");
        clinicianDTO.setUsername("testClinician");
        clinicianDTO.setEmail("testClinician@test.com");
        clinicianDTO.setPhone("1111111");
        clinicianDTO.setRole(1);

        String requestBody = objectMapper.writeValueAsString(clinicianDTO);

        Mockito.when(clinicianService.addClinicianAndGetByUsername(Mockito.any())).thenReturn(clinicianDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/clinician/new")
            .header("X-TenantID", 1)
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.clinicianId").value(1));
    }

    @Test
    public void testUpdateClinician() throws Exception {
        ClinicianDTO clinicianDTO = new ClinicianDTO();
        clinicianDTO.setTenantId(1);
        clinicianDTO.setClinicianId(1);
        clinicianDTO.setFirstName("Test");
        clinicianDTO.setLastName("Clinician");
        clinicianDTO.setPreferredName("A");
        clinicianDTO.setPassword("password");
        clinicianDTO.setUsername("testClinician");
        clinicianDTO.setEmail("testClinician@test.com");
        clinicianDTO.setPhone("1111111");
        clinicianDTO.setRole(1);

        String requestBody = objectMapper.writeValueAsString(clinicianDTO);

        Mockito.when(clinicianService.updateClinician(Mockito.any())).thenReturn(clinicianDTO);
        Mockito.when(clinicianService.getClinicianByUsername(Mockito.anyString())).thenReturn(Optional.of(clinicianDTO));
        this.mockMvc.perform(MockMvcRequestBuilders.put("/clinician/update")
                .header("X-TenantID", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.clinicianId").value(1));
    }

    @Test
    public void testDeleteClinician() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/clinician/delete/{id}", 1).header("X-TenantID", 1).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.deleted").value(true));
    }
}
