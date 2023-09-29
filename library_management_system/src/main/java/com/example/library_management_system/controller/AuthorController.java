package com.example.library_management_system.controller;

import com.example.library_management_system.dto.Response.BookResponse;
import com.example.library_management_system.model.Author;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // get the list of book written by a specific author
    @GetMapping("/list-of-books-by-id")
    public ResponseEntity getListOfBooksById(@RequestParam("authorId") int authorId) {

        try{
            List<BookResponse> bookList = authorService.getListOfBooksById(authorId);
            return new ResponseEntity(bookList,HttpStatus.FOUND);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
