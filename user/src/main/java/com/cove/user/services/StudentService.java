package com.cove.user.services;

import com.cove.user.dto.model.ClinicianDTO;
import com.cove.user.dto.model.StudentDTO;
import com.cove.user.exception.UserNotFoundException;

import java.util.List;

public interface StudentService {
    public List<StudentDTO> getAllStudents();
    public List<StudentDTO> getAllStudentsForClinician(long clinicianId);
    public StudentDTO getStudentById(long studentId);
    public StudentDTO addStudent(StudentDTO studentDTO);
    public StudentDTO updateStudentProfile(StudentDTO studentDTO) throws UserNotFoundException;
    public StudentDTO assignClinianByStudentId(long studentId, ClinicianDTO clinicianDTO) throws UserNotFoundException;
}
