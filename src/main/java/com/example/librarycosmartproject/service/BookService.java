package com.example.librarycosmartproject.service;

import com.example.librarycosmartproject.common.Connection;
import com.example.librarycosmartproject.common.DateUtil;
import com.example.librarycosmartproject.dto.AuthorDTO;
import com.example.librarycosmartproject.dto.BookDTO;
import com.example.librarycosmartproject.dto.BookResponseDTO;
import com.example.librarycosmartproject.dto.SubjectResponseDTO;
import com.example.librarycosmartproject.entity.Author;
import com.example.librarycosmartproject.entity.Book;
import com.example.librarycosmartproject.entity.BookAuthor;
import com.example.librarycosmartproject.entity.Subject;
import com.example.librarycosmartproject.repository.AuthorRepository;
import com.example.librarycosmartproject.repository.BookAuthorRepository;
import com.example.librarycosmartproject.repository.BookRepository;
import com.example.librarycosmartproject.repository.SubjectRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final SubjectService subjectService;
    private final AuthorService authorService;
    private final BookAuthorService bookAuthorService;
    private final Connection connection;
    private final ObjectMapper objectMapper;
    private final DateUtil dateUtil;

    public List<BookDTO> getListBooks(String subjectName) {
        Subject subject = subjectService.getSubjectByName(subjectName);
        List<Book> listBooks = bookRepository.findAllBooksBySubjects(subject.getName())
                .filter(books -> !books.isEmpty())
                .orElseGet(() -> getListBooksFromWebsite(subjectName, subject));
       return convertToResponseList(listBooks);
    }

    private List<Book> getListBooksFromWebsite(String subjectName, Subject subject) {
        ResponseEntity<String> responseUrl = connection.httpGet("https://openlibrary.org/subjects/" + subjectName + ".json");
        return Optional.ofNullable(responseUrl)
                .map(HttpEntity::getBody)
                .map(convertToListBookBySubject())
                .map(books -> convertToListBook(books.getWorks(), subject))
                .orElse(new ArrayList<>());
    }

    private Function<String, SubjectResponseDTO> convertToListBookBySubject() {
        return responseEntity -> {
            SubjectResponseDTO baseResponse = null;
            try {
                baseResponse = objectMapper.readValue(responseEntity, new TypeReference<>(){});
            } catch (JsonProcessingException e) {
                log.warn("error : {}", e.getMessage());
            }
            return baseResponse;
        };
    }

    private List<Book> convertToListBook(List<BookResponseDTO> list, Subject subject) {
        List<Book> listBooks = new ArrayList<>();

        list.forEach(book -> {
            Book newBook = new Book();
            newBook.setTitle(book.getTitle());
            newBook.setEditionNumber(book.getEditionCount());
            newBook.setCreateDate(new Date());
            newBook.setUpdateDate(new Date());
            newBook.setStatus(book.getAvailability().getStatus());
            newBook.setPublishYear(dateUtil.stringToDate(String.valueOf(book.getFirstPublishYear()), "yyyy"));
            newBook.setLastLoanDate(book.getAvailability().getLastLoanDate());
            newBook.setSubject(subject);
            listBooks.add(bookRepository.save(newBook));

            List<AuthorDTO> authors = book.getAuthors();
            authors.forEach(author -> {
                Author newAuthor = authorService.getAuthorByName(author.getName());
                bookAuthorService.saveBookAuthor(newBook, newAuthor);
            });
        });
        return listBooks;
    }

    private List<BookDTO> convertToResponseList(List<Book> list) {
        List<BookDTO> dtoList = new ArrayList<>();
        list.forEach(book -> {
            List<String> authorList = authorService.getListAuthorByBookTitle(book.getTitle());
            BookDTO bookDTO = new BookDTO();
            bookDTO.setTitle(book.getTitle());
            bookDTO.setId(book.getId());
            bookDTO.setAuthors(authorList);
            bookDTO.setEdition(book.getEditionNumber());
            dtoList.add(bookDTO);
        });
        return dtoList;
    }
}
