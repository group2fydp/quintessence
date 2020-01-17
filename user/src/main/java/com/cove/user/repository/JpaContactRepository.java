package com.cove.user.repository;

import com.cove.user.model.entities.Contact;
import com.cove.user.model.entities.Student;

import java.util.List;

public interface JpaContactRepository extends JpaStudentEntityRepository<Contact> {
    List<Contact> findAllByStudentByContactType(Student student, int contactType);
}
