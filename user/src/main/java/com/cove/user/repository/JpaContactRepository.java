package com.cove.user.repository;

import com.cove.user.model.entities.Contact;
import com.cove.user.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JpaContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findAllByStudentAndType(Student student, int contactType);
    List<Contact> findAllByStudent(Student student);
}
