package com.cove.user.services;

import com.cove.user.dto.model.ClinicianDTO;
import com.cove.user.dto.model.StudentDTO;
import com.cove.user.dto.model.UserRequestDTO;
import com.cove.user.dto.model.UserResponseDTO;
import com.cove.user.model.entities.TenantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AuthenticationService {
    @Autowired
    private ClinicianService clinicianService;

    @Autowired
    private StudentService studentService;

    public UserResponseDTO searchUser(UserRequestDTO userRequestDTO){
        if (userRequestDTO.getType().equals("CLINICIAN")){
            return mapClinicianToResponseDTO(clinicianService.getClinicianByUsername(userRequestDTO.getUsername()));
        }
        else if (userRequestDTO.getType().equals("STUDENT")){
            return mapStudentToResponseDTO( studentService.getStudentByUsername(userRequestDTO.getUsername()));
        }
        else {
            throw new EntityNotFoundException("User not found: " + userRequestDTO.getUsername());
        }
    }

    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO){
        if (userRequestDTO.getType().equals("CLINICIAN")){
            ClinicianDTO clinicianDTO = clinicianService.getClinicianByUsername(userRequestDTO.getUsername());
            clinicianDTO.setLoginAttempt(userRequestDTO.getLoginAttempt());
            return mapClinicianToResponseDTO(clinicianService.updateClinician(clinicianDTO));
        }
        else if (userRequestDTO.getType().equals("STUDENT")){
            StudentDTO studentDTO = studentService.getStudentByUsername(userRequestDTO.getUsername());
            studentDTO.setLoginAttempt(userRequestDTO.getLoginAttempt());
            return mapStudentToResponseDTO(studentDTO);
        }
        else {
            throw new EntityNotFoundException("User not found: " + userRequestDTO.getUsername());
        }
    }

    public UserResponseDTO fetchUserByUsername(String username){
        return clinicianService.getClinicianByUsername(username) != null ?
                mapClinicianToResponseDTO(clinicianService.getClinicianByUsername(username)) :
                mapStudentToResponseDTO(studentService.getStudentByUsername(username));
    }

    private UserResponseDTO mapClinicianToResponseDTO(ClinicianDTO clinicianDTO){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setEmailAddress(clinicianDTO.getEmail());
        userResponseDTO.setId(clinicianDTO.getClinicianId());
        userResponseDTO.setPassword(clinicianDTO.getPassword());
        userResponseDTO.setUsername(clinicianDTO.getUsername());
        userResponseDTO.setLoginAttempt(clinicianDTO.getLoginAttempt());
        userResponseDTO.setType("CLINICIAN");
        return userResponseDTO;
    }

    private UserResponseDTO mapStudentToResponseDTO(StudentDTO studentDTO){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setEmailAddress(studentDTO.getStudentEmail());
        userResponseDTO.setId(studentDTO.getStudentId());
        userResponseDTO.setPassword(studentDTO.getPassword());
        userResponseDTO.setUsername(studentDTO.getUsername());
        userResponseDTO.setLoginAttempt(studentDTO.getLoginAttempt());
        userResponseDTO.setType("STUDENT");
        return userResponseDTO;
    }
}
