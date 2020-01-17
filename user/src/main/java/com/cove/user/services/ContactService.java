package com.cove.user.services;

import com.cove.user.dto.model.ContactDTO;

import java.util.List;

public interface ContactService {
    List<ContactDTO> getAllContactsForStudent(long studentId);
    List<ContactDTO> getAllForStudentByContactType(long studentId, int contactType);
    ContactDTO addContact(ContactDTO contactDTO);
    ContactDTO updateContact(ContactDTO contactDTO) throws ContactNotFoundException;
    void deleteContact(long contactId);
}
