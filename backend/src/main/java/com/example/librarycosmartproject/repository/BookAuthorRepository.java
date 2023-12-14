package com.example.librarycosmartproject.repository;

import com.example.librarycosmartproject.entity.Author;
import com.example.librarycosmartproject.entity.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Integer> {
}
