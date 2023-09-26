package com.example.library_management_system.repository;

import com.example.library_management_system.Enum.Gender;
import com.example.library_management_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByGender(Gender gender);
}
