package com.example.library_management_system.service;

import com.example.library_management_system.Enum.TransactionStatus;
import com.example.library_management_system.Transformer.BookTransfrom;
import com.example.library_management_system.dto.Response.BookResponse;
import com.example.library_management_system.dto.Response.IssueBookResponse;
import com.example.library_management_system.dto.Response.StudentResponse;
import com.example.library_management_system.exceptions.BookNotAvailableException;
import com.example.library_management_system.exceptions.StudentNotFoundException;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.Student;
import com.example.library_management_system.model.Transaction;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.repository.StudentRepository;
import com.example.library_management_system.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    JavaMailSender javaMailSender;
    public IssueBookResponse issueBook(int bookId, int studentId) {

        // check if book exists
        Optional< Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty()) {
            throw new BookNotAvailableException("Invalid book id!");
        }

        // if book is already issued
        if(bookOptional.get().isIssued()) {
            throw new BookNotAvailableException("This book is already issued!");
        }

        Book book = bookOptional.get();

        // check if student exists
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isEmpty()) {
            throw new StudentNotFoundException("Invalid student id!");
        }

        Student student = studentOptional.get();

        Transaction transaction = Transaction.builder()
                .transactionNumber(String.valueOf(UUID.randomUUID()))
                .transactionStatus(TransactionStatus.SUCCESS)
                .book(book)
                .libraryCard(student.getLibraryCard())
                .build();

        Transaction savedTransaction = transactionRepo.save(transaction);

        // update book
        book.setIssued(true);
        book.getTransactions().add(savedTransaction);

        // update library card
        student.getLibraryCard().getTransactions().add(savedTransaction);

        Book savedBook = bookRepository.save(book);
        Student savedStudent = studentRepository.save(student);

        // send email

        String text = "Hi! " + savedStudent.getName() + " The below book has been issued to you\n" +
                savedBook.getTitle() + "\non" + savedTransaction.getTransactionTime() + " \nThis is the transaction number: "+savedTransaction.getTransactionNumber();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("iamlearningwithanki@gmail.com");
        simpleMailMessage.setTo(savedStudent.getEmail());
        simpleMailMessage.setSubject("Congrats " + savedStudent.getName() + "!! Your Book has been Issued");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);



        // response
        return IssueBookResponse.builder()
                .bookName(savedBook.getTitle())
                .transactionStatus(savedTransaction.getTransactionStatus())
                .transactionTime(savedTransaction.getTransactionTime())
                .transactionNumber(savedTransaction.getTransactionNumber())
                .studentName(savedStudent.getName())
                .libraryCardNumber(savedStudent.getLibraryCard().getCardNo())
                .authorName(savedBook.getAuthor().getName())
                .build();

    }

    public BookResponse returnBook(int studentId, int bookId) {
        // check if student exists
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isEmpty()) throw new StudentNotFoundException("Invalid student id!");

        // check if book id is valid
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty()) throw new BookNotAvailableException("Invalid book id");

        // change book status
        Book book = bookOptional.get();
        book.setIssued(false);
        // save changes
        Book savedBook = bookRepository.save(book);

//        // delete saved book from student's book issued list
//        for(int i=0; i<studentOptional.get().getIssuedBooks().size(); i++) {
//            Book b = studentOptional.get().getIssuedBooks().get(i);
//            if(b.getId()==bookId) {
//                studentOptional.get().getIssuedBooks().remove(i);
//                break;
//            }
//        }

        return BookTransfrom.bookToBookResponse(savedBook);
    }
}
