package com.example.library_management_system.service;

import com.example.library_management_system.Transformer.BookTransfrom;
import com.example.library_management_system.dto.Response.BookResponse;
import com.example.library_management_system.exceptions.AuthorNotFoundException;
import com.example.library_management_system.model.Author;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String addAuthor(Author author) {
        Author savedAuthor = authorRepository.save(author);
        return "author added!!";
    }

    public List<BookResponse> getListOfBooksById(int authorId) {
        // check if author exists
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if(authorOptional.isEmpty()) {
            throw new AuthorNotFoundException("Invalid Author Id!");
        }
        // get the list
        Author author = authorOptional.get();
        List<BookResponse> list = new ArrayList<>();
        for(Book b: author.getBookList()) {
            list.add(BookTransfrom.bookToBookResponse(b));
        }
        return list;
    }
}
