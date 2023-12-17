package com.example.librarycosmartproject.service;

import com.example.librarycosmartproject.entity.Author;
import com.example.librarycosmartproject.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Author Service Unit test")
class AuthorServiceTest {
    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    private Author author;

    @BeforeEach
    void setup(){
        author = new Author();
        author.setName("Farhan");
        author.setCreateDate(new Date());
        author.setUpdateDate(new Date());
    }

    @DisplayName("Get author by name")
    @Test
    void getAuthorByName() {
        Mockito.when(authorRepository.findAuthorByName(Mockito.anyString())).thenReturn(Optional.of(author));
        Author saveAuthor = authorService.getAuthorByName("Farhan");
        Mockito.verify(authorRepository, Mockito.times(1)).findAuthorByName(Mockito.anyString());
        Assertions.assertEquals(saveAuthor.getName(), author.getName());
    }

    @DisplayName("Get all authors by book title")
    @Test
    void getListAuthorByBookTitle() {
        Mockito.when(authorRepository.findAuthorsNameByBookTitle(Mockito.anyString())).thenReturn(Optional.of(List.of("Farhan")));
        List<String> listAuthor = authorService.getListAuthorByBookTitle("Hikayat Cinta");
        Mockito.verify(authorRepository, Mockito.times(1)).findAuthorsNameByBookTitle(Mockito.anyString());
        Assertions.assertEquals(listAuthor.get(0), author.getName());
    }

    @DisplayName("Save Author")
    @Test
    void saveNewAuthor() {
        Mockito.when(authorRepository.findAuthorByName(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(authorRepository.save(Mockito.any(Author.class))).thenReturn(author);
        Author saveAuthor = authorService.getAuthorByName("Farhan");
        Mockito.verify(authorRepository, Mockito.times(1)).save(Mockito.any(Author.class));
        Assertions.assertEquals(saveAuthor.getName(), author.getName());
    }
}