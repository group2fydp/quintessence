package com.cove.user.repository;

import com.cove.user.model.entities.Student;
import com.cove.user.model.entities.WarningSign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaWarningSignRepository extends JpaRepository<WarningSign, Long> {
    List<WarningSign> findAllByStudent(Student student);
}
