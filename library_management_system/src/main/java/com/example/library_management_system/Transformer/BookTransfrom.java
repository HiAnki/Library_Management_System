package com.example.library_management_system.Transformer;

import com.example.library_management_system.dto.Request.BookRequest;
import com.example.library_management_system.dto.Response.BookResponse;
import com.example.library_management_system.model.Book;

import lombok.Builder;


public class BookTransfrom {
    public static Book bookRequestToBook(BookRequest bookRequest) {
        return Book.builder()
                .title(bookRequest.getTitle())
                .cost(bookRequest.getCost())
                .genre(bookRequest.getGenre())
                .noOfPages(bookRequest.getNumberOfPages())
                .author(null)
                .build();
    }

    public static BookResponse bookToBookResponse(Book book) {
        return BookResponse.builder()
                .title(book.getTitle())
                .noOfPages((book.getNoOfPages()))
                .authorName(book.getAuthor().getName())
                .cost(book.getCost())
                .genre(book.getGenre())
                .issued(book.isIssued())
                .build();
    }
}
