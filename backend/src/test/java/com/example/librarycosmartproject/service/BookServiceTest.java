package com.example.librarycosmartproject.service;

import com.example.librarycosmartproject.common.Connection;
import com.example.librarycosmartproject.common.DateUtil;
import com.example.librarycosmartproject.dto.*;
import com.example.librarycosmartproject.entity.Author;
import com.example.librarycosmartproject.entity.Book;
import com.example.librarycosmartproject.entity.BookAuthor;
import com.example.librarycosmartproject.entity.Subject;
import com.example.librarycosmartproject.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Book Service Unit test")
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private SubjectService subjectService;

    @Mock
    private AuthorService authorService;

    @Mock
    private BookAuthorService bookAuthorService;

    @Mock
    private Connection connection;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private DateUtil dateUtil;

    private Subject subject;
    private Book book;

    private BookDTO bookDTO;

    @BeforeEach
    void setUp() {
        subject = new Subject();
        subject.setName("Love");
        subject.setId(1);

        book = new Book();
        book.setId(1);
        book.setTitle("Hikayat cinta");

        bookDTO = new BookDTO();
        bookDTO.setId(1);
        bookDTO.setTitle("Hikayat cinta");
        bookDTO.setAuthors(List.of("Farhan"));
    }

    @Test
    void getListBooks() {
        Mockito.when(subjectService.getSubjectByName(Mockito.anyString())).thenReturn(subject);
        Mockito.when(bookRepository.findAllBooksBySubjects(Mockito.anyString())).thenReturn(Optional.of(List.of(book)));
        Mockito.when(authorService.getListAuthorByBookTitle(Mockito.anyString())).thenReturn(List.of("Farhan"));
        List<BookDTO> listBooks = bookService.getListBooks("Love");
        Assertions.assertEquals(listBooks.get(0).getTitle(), book.getTitle());
    }

    @Test
    void getListBooksFromWebsite() throws JsonProcessingException {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Hikayat Cinta");

        Author author = new Author();
        author.setId(1);
        author.setName("farhan");

        BookAuthor bookAuthor = new BookAuthor();
        bookAuthor.setBook(book);
        bookAuthor.setAuthor(author);

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("Farhan");

        AvailabilityDTO availabilityDTO = new AvailabilityDTO();
        availabilityDTO.setStatus("available");
        availabilityDTO.setLastLoanDate(new Date());

        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setTitle("Hikayat Cinta");
        bookResponseDTO.setEditionCount("2121");
        bookResponseDTO.setFirstPublishYear(1968);
        bookResponseDTO.setAvailability(availabilityDTO);
        bookResponseDTO.setAuthors(List.of(authorDTO));

        SubjectResponseDTO subjectResponseDTO = new SubjectResponseDTO();
        subjectResponseDTO.setName("love");
        subjectResponseDTO.setWorks(List.of(bookResponseDTO));

        Mockito.when(subjectService.getSubjectByName(Mockito.anyString())).thenReturn(subject);
        Mockito.when(bookRepository.findAllBooksBySubjects(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(connection.httpGet(Mockito.anyString())).thenReturn(new ResponseEntity<String>("ok", HttpStatus.OK));
        Mockito.when(objectMapper.readValue(Mockito.anyString(), Mockito.any(TypeReference.class))).thenReturn(subjectResponseDTO);
        Mockito.when(dateUtil.stringToDate(Mockito.anyString(), Mockito.anyString())).thenReturn(new Date());
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
        Mockito.when(authorService.getAuthorByName(Mockito.anyString())).thenReturn(author);
        Mockito.when(bookAuthorService.saveBookAuthor(Mockito.any(Book.class), Mockito.any(Author.class))).thenReturn(bookAuthor);
        Mockito.when(authorService.getListAuthorByBookTitle(Mockito.anyString())).thenReturn(List.of("Farhan"));
        List<BookDTO> listBooks = bookService.getListBooks("Love");
        Assertions.assertEquals(listBooks.get(0).getTitle(), book.getTitle());
    }
}