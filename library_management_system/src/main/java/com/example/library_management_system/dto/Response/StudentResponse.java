package com.example.library_management_system.dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StudentResponse {
    String name;

    String email;

    String message;

    String cardNumber;
}
