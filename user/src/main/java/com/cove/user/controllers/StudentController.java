package com.cove.user.controllers;

import com.cove.user.dto.model.ContactDTO;
import com.cove.user.dto.model.ReasonToLiveDTO;
import com.cove.user.dto.model.StudentDTO;
import com.cove.user.dto.model.WarningSignDTO;
import com.cove.user.services.ContactService;
import com.cove.user.services.ReasonToLiveService;
import com.cove.user.services.StudentService;
import com.cove.user.services.WarningSignService;
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

    @Autowired(required = false)
    private WarningSignService warningSignService;

    @Autowired(required = false)
    private ReasonToLiveService reasonToLiveService;

    @Autowired
    private Environment environment;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/all")
    public List<StudentDTO> getAllStudents(){
        List<StudentDTO> allStudents = studentService.getAllStudents();
        return allStudents;
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
    @RequestMapping("/{id}/warningSigns")
    public List<WarningSignDTO> getAllWarningSignsForStudent(@PathVariable final int id){
        return warningSignService.getAllWarningSignsForStudent(id);
    }

    @RequestMapping("/{id}/reasonsToLive")
    public List<ReasonToLiveDTO> getAllReasonsToLiveForStudent(@PathVariable final int id){
        return reasonToLiveService.getAllReasonsToLiveForStudent(id);
    }

    @PostMapping("/new")
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO){
        return studentService.addStudent(studentDTO);
    }

    @PutMapping("/update")
    public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(studentDTO);
    }

    @PostMapping("/contacts/new")
    public ContactDTO createContact(@RequestBody ContactDTO contactDTO) {
        return contactService.addContact(contactDTO);
    }

    @PostMapping("/warningSigns/new")
    public WarningSignDTO createWarningSign(@RequestBody WarningSignDTO warningSignDTO){
        return warningSignService.addWarningSign(warningSignDTO);
    }

    @PostMapping("/reasonsToLive/new")
    public ReasonToLiveDTO createReasonToLive(@RequestBody ReasonToLiveDTO reasonToLiveDTO){
        return reasonToLiveService.addReasonToLive(reasonToLiveDTO);
    }

    @PutMapping("/contacts/update")
    public ContactDTO updateContact(@RequestBody ContactDTO contactDTO) {
        return contactService.updateContact(contactDTO);
    }

    @PutMapping("/warningSigns/update")
    public WarningSignDTO updateWarningSign(@RequestBody WarningSignDTO warningSignDTO) {
        return warningSignService.updateWarningSign(warningSignDTO);
    }

    @PutMapping("/reasonsToLive/update")
    public ReasonToLiveDTO updateReasonToLive(@RequestBody ReasonToLiveDTO reasonToLiveDTO){
        return reasonToLiveService.updateReasonToLive(reasonToLiveDTO);
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

    @DeleteMapping("/warningSigns/delete/{id}")
    public Map<String, Boolean> deleteWarningSign(@PathVariable final int id){
        warningSignService.deleteWarningSign(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

    @DeleteMapping("/reasonsToLive/delete/{id}")
    public Map<String, Boolean> deleteReasonToLive(@PathVariable final int id){
        reasonToLiveService.deleteReasonToLive(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }
}
