package com.cove.user.services;

import com.cove.user.dto.model.ClinicianDTO;
import com.cove.user.dto.model.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(long studentId);
    StudentDTO addStudentAndGetStudentByUsername(StudentDTO studentDTO);
    void addStudent(StudentDTO studentDTO);
    StudentDTO updateStudent(StudentDTO studentDTO);
    StudentDTO assignClinianByStudentId(long studentId, ClinicianDTO clinicianDTO);
    String deleteStudent(long studentId);
    StudentDTO getStudentByUsername(String username);
}
