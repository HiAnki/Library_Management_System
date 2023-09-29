package com.example.library_management_system.model;

import com.example.library_management_system.Enum.Gender;
import com.example.library_management_system.dto.Response.BookResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int regNum;

    @Column(name = "student_name")
    String name;

    @Column(unique = true,nullable = false)
    String email;

    int age;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @JsonIgnore
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    LibraryCard libraryCard;



}
