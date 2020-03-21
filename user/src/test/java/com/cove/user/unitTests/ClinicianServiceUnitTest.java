package com.cove.user.unitTests;

import com.cove.safetyplan.util.DateUtils;
import com.cove.user.dto.model.ClinicianDTO;
import com.cove.user.dto.model.StudentDTO;
import com.cove.user.model.entities.Clinician;
import com.cove.user.model.entities.Student;
import com.cove.user.repository.JpaClinicianRepository;
import com.cove.user.repository.JpaStudentRepository;
import com.cove.user.services.ClinicianServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.MockitoAnnotations.initMocks;

public class ClinicianServiceUnitTest {
    @InjectMocks
    private ClinicianServiceImpl clinicianService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private BCryptPasswordEncoder encoder;

    @Mock
    private JpaClinicianRepository clinicianRepository;

    private Clinician clinician1 = new Clinician();
    private Clinician clinician2 = new Clinician();
    private ClinicianDTO clinicianDTO1 = new ClinicianDTO();
    private ClinicianDTO clinicianDTO2 = new ClinicianDTO();

    @BeforeEach
    public void setup() throws Exception {
        initMocks(this);
        clinicianDTO1.setClinicianId(1L);
        clinicianDTO1.setTenantId(1L);
        clinicianDTO1.setUsername("jDoe");
        clinicianDTO1.setFirstName("John");
        clinicianDTO1.setLastName("Doe");
        clinicianDTO1.setPhone("1111111");
        clinicianDTO1.setEmail("test@test.com");
        clinicianDTO1.setPreferredName("A");
        clinicianDTO1.setRole(1);

        clinicianDTO2.setClinicianId(2L);
        clinicianDTO2.setTenantId(1L);
        clinicianDTO2.setUsername("janeDoe");
        clinicianDTO2.setFirstName("Jane");
        clinicianDTO2.setLastName("Doe");
        clinicianDTO2.setPhone("2222222");
        clinicianDTO2.setEmail("test1@test.com");
        clinicianDTO2.setPreferredName("B");
        clinicianDTO2.setRole(1);

        clinician2.setClinicianId(2L);
        clinician2.setTenantId(1L);
        clinician2.setUsername("janeDoe");
        clinician2.setFirstName("Jane");
        clinician2.setLastName("Doe");
        clinician2.setPhone("2222222");
        clinician2.setEmail("test1@test.com");
        clinician2.setPreferredName("B");
        clinician2.setRole(1);
        clinician2.setLastModifyDate(DateUtils.today());
        clinician2.setCreateDate(DateUtils.today());
    }

    @Test
    public void testGetClinicianById(){
        Mockito.when(clinicianRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(clinician1));
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(clinicianDTO1);
        ClinicianDTO result = clinicianService.getClinicianById(1L);
        Assertions.assertEquals(1L, result.getClinicianId());
        Assertions.assertEquals("John",result.getFirstName());
        Assertions.assertEquals("Doe", result.getLastName());
        Assertions.assertEquals("1111111", result.getPhone());
        Assertions.assertEquals("test@test.com", result.getEmail());
        Assertions.assertEquals("A", result.getPreferredName());
        Assertions.assertEquals(1, result.getRole());
        Assertions.assertEquals("jDoe", result.getUsername());
        Assertions.assertNotNull(result);
    }

    @Test
    public void testGetClinicianByUsername(){
        Mockito.when(clinicianRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(clinician1));
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(clinicianDTO1);
        ClinicianDTO result = clinicianService.getClinicianByUsername("jDoe").get();
        Assertions.assertEquals(1L, result.getClinicianId());
        Assertions.assertEquals("John",result.getFirstName());
        Assertions.assertEquals("Doe", result.getLastName());
        Assertions.assertEquals("1111111", result.getPhone());
        Assertions.assertEquals("test@test.com", result.getEmail());
        Assertions.assertEquals("A", result.getPreferredName());
        Assertions.assertEquals(1, result.getRole());
        Assertions.assertEquals("jDoe", result.getUsername());
        Assertions.assertNotNull(result);
    }

    @Test
    public void testAddClinician(){
        Mockito.when(clinicianRepository.save(Mockito.any())).thenReturn(clinician2);
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(clinician2);
        clinicianService.addClinician(clinicianDTO2);

        Mockito.when(clinicianRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(clinician2));
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(clinicianDTO2);
        ClinicianDTO result = clinicianService.getClinicianById(clinicianDTO2.getClinicianId());

        Assertions.assertEquals(2L, result.getClinicianId());
        Assertions.assertEquals("Jane",result.getFirstName());
        Assertions.assertEquals("Doe", result.getLastName());
        Assertions.assertEquals("2222222", result.getPhone());
        Assertions.assertEquals("test1@test.com", result.getEmail());
        Assertions.assertEquals("B", result.getPreferredName());
        Assertions.assertEquals(1, result.getRole());
        Assertions.assertEquals("janeDoe", result.getUsername());
        Assertions.assertNotNull(result);
    }

    @Test
    public void testUpdateClinician(){
        clinician1.setFirstName("Joe");
        clinician1.setLastName("Dow");
        clinician1.setPhone("2222222");
        clinician1.setEmail("test1@test.com");
        clinician1.setPreferredName("B");

        clinicianDTO1.setFirstName("Joe");
        clinicianDTO1.setLastName("Dow");
        clinicianDTO1.setPhone("2222222");
        clinicianDTO1.setEmail("test1@test.com");
        clinicianDTO1.setPreferredName("B");

        Mockito.when(clinicianRepository.save(Mockito.any())).thenReturn(clinician1);
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(clinicianDTO1);
        Mockito.when(clinicianRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(clinician1));

        ClinicianDTO result = clinicianService.updateClinician(clinicianDTO1);

        Assertions.assertEquals(1L, result.getClinicianId());
        Assertions.assertEquals("Joe",result.getFirstName());
        Assertions.assertEquals("Dow", result.getLastName());
        Assertions.assertEquals("2222222", result.getPhone());
        Assertions.assertEquals("test1@test.com", result.getEmail());
        Assertions.assertEquals("B", result.getPreferredName());
        Assertions.assertEquals(1, result.getRole());
        Assertions.assertEquals("jDoe", result.getUsername());
        Assertions.assertNotNull(result);
    }

    @Test
    public void testDeleteClinician(){
        Mockito.when(clinicianRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(clinician1));
        String response = clinicianService.deleteClinician(1L);
        Assertions.assertEquals("SUCCESS", response);
    }
}
