package com.example.library_management_system.controller;

import com.example.library_management_system.dto.Request.StudentRequest;
import com.example.library_management_system.dto.Response.StudentResponse;
import com.example.library_management_system.model.Student;
import com.example.library_management_system.service.StudentService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    // add student
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody StudentRequest studentRequest) {
        StudentResponse response = studentService.addStudent(studentRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    // get student
    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int regNo) {
        Student student = studentService.getStudent(regNo);
        if(student!=null) {
            return new ResponseEntity(student,HttpStatus.FOUND);
        }
        return new ResponseEntity("Invalid Id!", HttpStatus.BAD_REQUEST);

    }
    // delete student by reg number
    @DeleteMapping("/Delete")
    public ResponseEntity deleteStudentByRegNo(@RequestParam("id") int regNo){
        studentService.deleteStudentByRegNo(regNo);
        return new ResponseEntity("Student deleted!", HttpStatus.OK);
    }

    // update student age by reg no
    // get all the students in db
    // get the list of all male students
    @GetMapping("/get-all-males")
    public List<String> getAllMales() {
        List<String> allMales = studentService.getAllMales();
        return allMales;
    }
}
