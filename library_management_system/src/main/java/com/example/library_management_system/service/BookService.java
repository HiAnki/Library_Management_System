package com.example.library_management_system.service;

import com.example.library_management_system.Transformer.BookTransfrom;
import com.example.library_management_system.dto.Request.BookRequest;
import com.example.library_management_system.dto.Response.BookResponse;
import com.example.library_management_system.exceptions.AuthorNotFoundException;
import com.example.library_management_system.exceptions.BookNotAvailableException;
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

    // delete book
    public BookResponse deleteBook(int bookId) {

        // check if book exists
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty()) {
            throw new BookNotAvailableException("Invalid Book Id!");
        }

        Book book = bookOptional.get();
        if(book.isIssued()) throw new BookNotAvailableException("Book is issued! Can not be deleted!");

        // delete book from author
        List<Book> bookList = authorRepository.findById(book.getAuthor().getId()).get().getBookList();

        // delete book from list
        for(int i=0; i<bookList.size(); i++) {
            Book listBook = bookList.get(i);
            // check if bookId same
            if(listBook.getId()==bookId) {
                bookList.remove(i);
                break;
            }
        }

        // delete book from repository
        bookRepository.deleteById(bookId);
        return BookTransfrom.bookToBookResponse(book);
    }
}
