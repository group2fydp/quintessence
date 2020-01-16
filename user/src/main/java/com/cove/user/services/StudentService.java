package com.cove.user.services;

import com.cove.user.dto.model.ClinicianDTO;
import com.cove.user.dto.model.StudentDTO;
import com.cove.user.exception.UserNotFoundException;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(long studentId);
    StudentDTO addStudent(StudentDTO studentDTO);
    StudentDTO updateStudent(StudentDTO studentDTO) throws UserNotFoundException;
    StudentDTO assignClinianByStudentId(long studentId, ClinicianDTO clinicianDTO) throws UserNotFoundException;
    void deleteStudent(long studentId);
}
