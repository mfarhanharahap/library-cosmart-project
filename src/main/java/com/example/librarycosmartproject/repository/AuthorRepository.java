package com.example.librarycosmartproject.repository;

import com.example.librarycosmartproject.entity.Author;
import com.example.librarycosmartproject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query(value = " select a.* from author a where a.name ilike :authorName",
            nativeQuery = true)
    Optional<Author> findAuthorByName(@Param("authorName") String authorName);


    @Query(value = " select a.name from book_author ba " +
            "join book b on ba.book_id=b.id " +
            "join author a on ba.author_id=a.id " +
            "where b.title = :bookTitle ",
            nativeQuery = true)
    Optional<List<String>> findAuthorsNameByBookTitle(@Param("bookTitle") String bookTitle);
}
