package com.example.library_management_system.controller;

import com.example.library_management_system.dto.Request.BookRequest;
import com.example.library_management_system.dto.Response.BookResponse;
import com.example.library_management_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String addBook(@RequestBody BookRequest bookRequest) {

        try{
            String response = bookService.addBook(bookRequest);
            return response;
        }catch (Exception e) {
            return e.getMessage();
        }
    }

    // delete a book
    @DeleteMapping("/delete")
    public ResponseEntity deleteBook(@RequestParam("bookId") int bookId) {

        try{
            BookResponse bookResponse = bookService.deleteBook(bookId);
            return new ResponseEntity(bookResponse,HttpStatus.FOUND);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // return names of all the books of a particular genre and cost greater than x rs

    @GetMapping("/get-by-genre-cost")
    public List<BookResponse> getBooksByGenreAndCost(@RequestParam("genre") String genre, @RequestParam("cost") double cost) {
        return bookService.getBookByGenreAndCost(genre,cost);
    }

}
