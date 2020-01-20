package com.cove.user.repository;

import com.cove.user.model.entities.ReasonToLive;
import com.cove.user.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaReasonToLiveRepository extends JpaRepository<ReasonToLive, Long> {
    List<ReasonToLive> findAllByStudent(Student student);
}
