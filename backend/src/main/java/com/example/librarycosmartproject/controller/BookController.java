package com.example.librarycosmartproject.controller;

import com.example.librarycosmartproject.dto.BookDTO;
import com.example.librarycosmartproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/get-books")
    public ResponseEntity<List<BookDTO>> getAllBooksBySubject(String subjectName){
        return ResponseEntity.ok(bookService.getListBooks(subjectName));
    }
}
