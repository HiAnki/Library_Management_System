package com.example.library_management_system.controller;

import com.example.library_management_system.model.Author;
import com.example.library_management_system.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody Author author) {
        String author1 = authorService.addAuthor(author);
        return new ResponseEntity(author1, HttpStatus.CREATED);
    }
}
