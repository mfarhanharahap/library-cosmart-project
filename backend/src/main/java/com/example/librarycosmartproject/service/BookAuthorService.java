package com.example.librarycosmartproject.service;

import com.example.librarycosmartproject.entity.Author;
import com.example.librarycosmartproject.entity.Book;
import com.example.librarycosmartproject.entity.BookAuthor;
import com.example.librarycosmartproject.repository.BookAuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookAuthorService {
    private final BookAuthorRepository bookAuthorRepository;

    public BookAuthor saveBookAuthor(Book book, Author author) {
        BookAuthor bookAuthor = new BookAuthor();
        bookAuthor.setBook(book);
        bookAuthor.setAuthor(author);
        bookAuthor.setCreateDate(new Date());
        bookAuthor.setUpdateDate(new Date());
        return bookAuthorRepository.save(bookAuthor);
    }
}
