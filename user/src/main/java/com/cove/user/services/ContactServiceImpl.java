package com.cove.user.services;

import com.cove.user.dto.model.ContactDTO;
import com.cove.user.model.entities.Contact;
import com.cove.user.model.entities.Student;
import com.cove.user.repository.JpaContactRepository;
import com.cove.user.repository.JpaStudentRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private JpaContactRepository contactRepository;

    @Autowired
    private JpaStudentRepository studentRepository;

    @Autowired(required = false)
    private ModelMapper modelMapper;

    Converter<Contact, ContactDTO> customContactConverter = mappingContext -> {
        ContactDTO contactDTO = new ContactDTO();
        Contact contact = mappingContext.getSource();
        contactDTO.setStudentId(contact.getStudent().getStudentId());
        contactDTO.setType(contact.getType());
        contactDTO.setCellPhone(contact.getCellPhone());
        contactDTO.setContactId(contact.getContactId());
        contactDTO.setEmail(contact.getEmail());
        if (contact.getHomePhone() != null) {
            contactDTO.setHomePhone(contact.getHomePhone());
        }
        contactDTO.setName(contact.getName());
        contactDTO.setTextMsg(contact.getTextMsg());
        return contactDTO;
    };

    public List<ContactDTO> getAllContactsForStudent(long studentId){
        Optional<Student> student = studentRepository.findById(studentId);
        List<ContactDTO> contactDTOS = new ArrayList<>();
        modelMapper.addConverter(customContactConverter, Contact.class, ContactDTO.class);
        if (student.isPresent()){
           contactRepository.findAllByStudent(student.get())
                   .forEach(contact -> contactDTOS.add(modelMapper.map(contact, ContactDTO.class)));
        }
        return contactDTOS;
    }

    public List<ContactDTO> getAllForStudentByContactType(long studentId, int contactType){
        Optional<Student> student = studentRepository.findById(studentId);
        List<ContactDTO> contactDTOS = new ArrayList<>();
        modelMapper.createTypeMap(Contact.class, ContactDTO.class).setConverter(customContactConverter);
        if (student.isPresent()){
            contactRepository.findAllByStudentAndType(student.get(), contactType)
                    .forEach(c -> contactDTOS.add(modelMapper.map(c, ContactDTO.class)));
        }
        return contactDTOS;
    }

    public ContactDTO addContact(ContactDTO contactDTO){
        modelMapper.createTypeMap(Contact.class, ContactDTO.class).setConverter(customContactConverter);
        Contact contact = modelMapper.map(contactDTO, Contact.class);
        contact.setStudent(studentRepository.findById(contactDTO.getStudentId()).get());
        return modelMapper.map(contactRepository.save(contact), ContactDTO.class)
                .setStudentId(contact.getStudent().getStudentId());
    }

    public ContactDTO updateContact(ContactDTO contactDTO) {
        modelMapper.createTypeMap(Contact.class, ContactDTO.class).setConverter(customContactConverter);
        Optional<Contact> contact = contactRepository.findById(contactDTO.getContactId());
        if (contact.isPresent()){
            Contact contactModel = contact.get();
            contactModel.setName(contactDTO.getName());
            //TODO needs more improvement
            return modelMapper.map(contactRepository.save(contactModel), ContactDTO.class)
                    .setStudentId(contactModel.getStudent().getStudentId());
        }
        throw new EntityNotFoundException("Contact not found " + contactDTO.getContactId());
    }

    public void deleteContact(long contactId){
        Optional<Contact> contact = contactRepository.findById(contactId);
        if (contact.isPresent()){
            contactRepository.delete(contact.get());
        }
        else {
            throw new ResourceNotFoundException();
        }
    }
}
