package com.example.library_management_system.controller;

import com.example.library_management_system.dto.Response.BookResponse;
import com.example.library_management_system.dto.Response.IssueBookResponse;
import com.example.library_management_system.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @PostMapping("/issue/book-id/{book-id}/student-id/{student-id}")
    public ResponseEntity issueBook(@PathVariable("bookId") int bookId, @PathVariable("studentId") int studentId) {

        try{
            IssueBookResponse response = transactionService.issueBook(bookId,studentId);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
