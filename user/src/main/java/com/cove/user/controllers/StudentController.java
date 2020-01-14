package com.cove.user.controllers;

import com.cove.user.dto.model.StudentDTO;
import com.cove.user.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired(required = false)
    private StudentService studentService;

    @Autowired
    private Environment environment;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @RequestMapping("/{id}")
    public String getStudent(@PathVariable final int id){
        return "Student: " + String.valueOf(id);
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
}
