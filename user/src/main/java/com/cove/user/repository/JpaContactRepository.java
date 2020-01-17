package com.cove.user.repository;

import com.cove.user.dto.model.ContactDTO;
import com.cove.user.model.entities.Contact;
import com.cove.user.model.entities.Student;
import com.cove.user.model.enums.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findAllByStudent(Student student);
    List<Contact> findAllByStudentByContactType(Student student, int contactType);
}
