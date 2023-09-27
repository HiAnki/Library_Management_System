package com.example.library_management_system.model;

import com.example.library_management_system.Enum.CardStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int cardId;

    // cardno, card status, issue date, student

    String cardNo; // uuid

    @Enumerated(EnumType.STRING)
    CardStatus cardStatus;

    @CreationTimestamp
    Date issueDate;

    @JsonIgnore
    @OneToOne
    @JoinColumn
    Student student;

    @OneToMany(mappedBy = "libraryCard",cascade = CascadeType.ALL)
    List<Transaction> transactions = new ArrayList<>();

}
