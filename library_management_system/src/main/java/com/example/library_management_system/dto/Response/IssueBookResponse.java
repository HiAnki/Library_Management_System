package com.example.library_management_system.dto.Response;

import com.example.library_management_system.Enum.TransactionStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IssueBookResponse {

    String bookName;
    String authorName;

    String transactionNumber; //uuid

    Date transactionTime;

    String studentName;

    String libraryCardNumber;

    TransactionStatus transactionStatus;
}
