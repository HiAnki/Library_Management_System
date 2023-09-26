package com.example.library_management_system.service;

import com.example.library_management_system.model.Author;
import com.example.library_management_system.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String addAuthor(Author author) {
        Author savedAuthor = authorRepository.save(author);
        return "author added!!";
    }
}