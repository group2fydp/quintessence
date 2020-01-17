package com.cove.user.controllers;

import com.cove.user.dto.model.ContactDTO;
import com.cove.user.dto.model.StudentDTO;
import com.cove.user.exception.ContactNotFoundException;
import com.cove.user.exception.UserNotFoundException;
import com.cove.user.model.entities.Contact;
import com.cove.user.services.ContactService;
import com.cove.user.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    @Autowired(required = false)
    private StudentService studentService;

    @Autowired(required = false)
    private ContactService contactService;

    @Autowired
    private Environment environment;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/{id}")
    public StudentDTO getStudent(@PathVariable final int id){
        return studentService.getStudentById(id);
    }

    @RequestMapping("/{id}/contacts")
    public List<ContactDTO> getAllContactsForStudent(@PathVariable final int id){
        return contactService.getAllContactsForStudent(id);
    }

    @RequestMapping("/{id}/contacts/{type}")
    public List<ContactDTO> getAllContactsForStudentWithType(@PathVariable final int id, @PathVariable int type){
        return contactService.getAllForStudentByContactType(id, type);
    }

    @RequestMapping("/all")
    public List<StudentDTO> getAllStudents(){
        List<StudentDTO> allStudents = studentService.getAllStudents();
        return allStudents;
    }

    @PostMapping("/new")
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO){
        return studentService.addStudent(studentDTO);
    }

    @PutMapping("/update")
    public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO) throws UserNotFoundException {
        return studentService.updateStudent(studentDTO);
    }

    @PostMapping("/contacts/new")
    public ContactDTO createContact(@RequestBody ContactDTO contactDTO) throws ContactNotFoundException {
        return contactService.addContact(contactDTO);
    }

    @PutMapping("/contacts/update")
    public ContactDTO updateContact(@RequestBody ContactDTO contactDTO) throws ContactNotFoundException {
        return contactService.updateContact(contactDTO);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable final int id){
        studentService.deleteStudent(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

    @DeleteMapping("/contacts/delete/{id}")
    public Map<String, Boolean> deleteContact(@PathVariable final int id){
        contactService.deleteContact(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

}
