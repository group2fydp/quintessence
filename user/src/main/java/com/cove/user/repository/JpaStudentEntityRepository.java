package com.cove.user.repository;


import com.cove.user.model.entities.Student;
import com.cove.user.model.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaStudentEntityRepository<T extends StudentEntity> extends JpaRepository<T, Long> {
    Iterable<T> findAllByStudent(Student student);
}
