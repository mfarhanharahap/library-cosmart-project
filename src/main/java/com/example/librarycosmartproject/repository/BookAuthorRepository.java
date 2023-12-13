package com.example.librarycosmartproject.repository;

import com.example.librarycosmartproject.entity.Author;
import com.example.librarycosmartproject.entity.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Integer> {
//    @Query(value = " select ba.* from book_author ba " +
//            "join book b on ba.book_id=b.id " +
//            "join author a on ba.author_id=a.id " +
//            "where b.title = :bookTitle and a.name = :authorName",
//            nativeQuery = true)
//    Optional<BookAuthor> findAuthorAndBook(@Param("bookTitle") String bookTitle, @Param("authorName") String authorName);
}
