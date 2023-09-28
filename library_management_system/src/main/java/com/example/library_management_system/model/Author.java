package com.example.library_management_system.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    //name age email lastActive book
    String name;
    int age;

    @Column(unique = true,nullable = false)
    String emailId;

    @UpdateTimestamp
    Date lastActive;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    List<Book> bookList = new ArrayList<>();

}
