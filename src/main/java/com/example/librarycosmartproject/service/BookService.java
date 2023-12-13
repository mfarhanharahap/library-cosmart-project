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
    private final SubjectRepository subjectRepository;
    private final AuthorRepository authorRepository;
    private final BookAuthorRepository bookAuthorRepository;
    private final Connection connection;
    private final ObjectMapper objectMapper;
    private final DateUtil dateUtil;

    public List<BookDTO> getListBooks(String subjectName) {
        Subject subject = subjectRepository.findSubjectByName(subjectName)
                .orElseGet(() -> addSubject(subjectName));
        List<Book> listBooks = bookRepository.findAllBooksBySubjects(subject.getName())
                .filter(books -> !books.isEmpty())
                .orElseGet(() -> getListBooksFromWebsite(subjectName, subject));
       return convertToResponseList(listBooks);
    }

    private Subject addSubject(String subjectName) {
        Subject subject = new Subject();
        subject.setName(subjectName);
        subject.setCreateDate(new Date());
        subject.setUpdateDate(new Date());
        return subjectRepository.save(subject);
    }

    private Author addAuthor(String authorName) {
        Author author = new Author();
        author.setName(authorName);
        author.setCreateDate(new Date());
        author.setUpdateDate(new Date());
        return authorRepository.save(author);
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
                Author newAuthor = authorRepository.findAuthorByName(author.getName())
                        .orElseGet(() -> addAuthor(author.getName()));
                BookAuthor bookAuthor = new BookAuthor();
                bookAuthor.setBook(newBook);
                bookAuthor.setAuthor(newAuthor);
                bookAuthor.setCreateDate(new Date());
                bookAuthor.setUpdateDate(new Date());
                bookAuthorRepository.save(bookAuthor);
            });
        });
        return listBooks;
    }

    private List<BookDTO> convertToResponseList(List<Book> list) {
        List<BookDTO> dtoList = new ArrayList<>();
        list.forEach(book -> {
            List<String> authorList = authorRepository.findAuthorsNameByBookTitle(book.getTitle()).orElse(new ArrayList<>());
            BookDTO bookDTO = new BookDTO();
            bookDTO.setTitle(book.getTitle());
            bookDTO.setId(book.getId());
            bookDTO.setAuthors(authorList);
            dtoList.add(bookDTO);
        });
        return dtoList;
    }
}
