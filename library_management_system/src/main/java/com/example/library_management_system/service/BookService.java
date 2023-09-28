package com.example.library_management_system.service;

import com.example.library_management_system.Transformer.BookTransfrom;
import com.example.library_management_system.dto.Request.BookRequest;
import com.example.library_management_system.dto.Response.BookResponse;
import com.example.library_management_system.exceptions.AuthorNotFoundException;
import com.example.library_management_system.model.Author;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.repository.AuthorRepository;
import com.example.library_management_system.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    public String addBook(BookRequest bookRequest) {

        // does author exist?
        int authorId = bookRequest.getAuthorId();
        Optional<Author> authorOptional = authorRepository.findById(authorId);

        if(authorOptional.isEmpty()) {
            throw new AuthorNotFoundException("Invalid Author Id! "+authorId);
        }

        Author author = authorOptional.get();
        Book book = BookTransfrom.bookRequestToBook(bookRequest);
        book.setAuthor(author);

        author.getBookList().add(book);

        authorRepository.save(author); // saves both author and book

        return "Book added!!";
    }

    public List<BookResponse> getBookByGenreAndCost(String genre, double cost) {
        List<Book> bookList = bookRepository.getBookByGenreAndCost(genre,cost);

        List<BookResponse> responses = new ArrayList<>();

        for(Book book : bookList) {
            responses.add(BookTransfrom.bookToBookResponse(book));
        }
        return responses;
    }
}
