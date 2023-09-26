package com.example.library_management_system.service;

import com.example.library_management_system.Enum.CardStatus;
import com.example.library_management_system.Enum.Gender;
import com.example.library_management_system.dto.Request.StudentRequest;
import com.example.library_management_system.dto.Response.StudentResponse;
import com.example.library_management_system.model.LibraryCard;
import com.example.library_management_system.model.Student;
import com.example.library_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public StudentResponse addStudent(StudentRequest studentRequest) {
        Student student = new Student();

        // set student
        student.setName(studentRequest.getName());
        student.setAge(studentRequest.getAge());
        student.setGender(studentRequest.getGender());
        student.setEmail(studentRequest.getEmail());

        // create library card
        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setStudent(student);

        student.setLibraryCard(libraryCard);
        Student saveStudent = studentRepository.save(student); // saves both student and library card

        // create response
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setName(saveStudent.getName());
        studentResponse.setEmail(saveStudent.getEmail());
        studentResponse.setMessage("You are registered!!");
        studentResponse.setCardNumber(saveStudent.getLibraryCard().getCardNo());
        return studentResponse;
    }

    public Student getStudent(int regNo) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if(studentOptional.isPresent()) return studentOptional.get();
        return null;
    }

    public void deleteStudentByRegNo(int regNo) {
        studentRepository.deleteById(regNo);
    }

    public List<String> getAllMales() {
        Gender gender = Gender.valueOf("MALE");
        List<Student> allMales = studentRepository.findByGender(gender);
        List<String> allMaleNames = new ArrayList<>();

        for(Student std: allMales) {
            allMaleNames.add(std.getName());
        }

        return allMaleNames;
    }
}
