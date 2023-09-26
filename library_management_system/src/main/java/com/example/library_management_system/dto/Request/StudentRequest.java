package com.example.library_management_system.dto.Request;

import com.example.library_management_system.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StudentRequest {
    String name;

    String email;

    int age;

    Gender gender;
}
