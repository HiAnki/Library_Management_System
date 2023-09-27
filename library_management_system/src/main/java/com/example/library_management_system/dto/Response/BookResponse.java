package com.example.library_management_system.dto.Response;


import com.example.library_management_system.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookResponse {
    String title;
    int noOfPages;
    Genre genre;
    double cost;
    String authorName;
}
