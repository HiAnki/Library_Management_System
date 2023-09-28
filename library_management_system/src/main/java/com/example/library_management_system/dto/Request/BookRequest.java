package com.example.library_management_system.dto.Request;

import com.example.library_management_system.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookRequest {
    String title;

    int numberOfPages;

    Genre genre;

    double cost;

    int authorId;

    }

