package com.example.library_management_system.repository;

import com.example.library_management_system.Enum.Genre;
import com.example.library_management_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    // native sql query
    @Query(value = "select * from book where genre = :genre and cost > :cost", nativeQuery = true)
    List<Book> getBookByGenreAndCost(String genre,double cost);

    // hibernate query
//    @Query(value = "select b from Book b where b.genre = :genre and b.cost > :cost")
//    List<Book> getBooksByGenreAndCostGreaterThanHQL(Genre genre, double cost);


}
