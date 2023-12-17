package com.example.librarycosmartproject.service;

import com.example.librarycosmartproject.entity.Author;
import com.example.librarycosmartproject.entity.Book;
import com.example.librarycosmartproject.entity.BookAuthor;
import com.example.librarycosmartproject.repository.BookAuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Book Author Service Unit test")
class BookAuthorServiceTest {

    @InjectMocks
    private BookAuthorService bookAuthorService;

    @Mock
    private BookAuthorRepository bookAuthorRepository;

    private BookAuthor bookAuthor;

    @Test
    void saveBookAuthor() {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Hikayat Cinta");

        Author author = new Author();
        author.setId(1);
        author.setName("farhan");

        bookAuthor =  new BookAuthor();
        bookAuthor.setBook(book);
        bookAuthor.setAuthor(author);

        Mockito.when(bookAuthorRepository.save(Mockito.any(BookAuthor.class))).thenReturn(bookAuthor);
        BookAuthor saveBookAuthor = bookAuthorService.saveBookAuthor(book, author);
        Mockito.verify(bookAuthorRepository, Mockito.times(1)).save(Mockito.any(BookAuthor.class));
        Assertions.assertEquals(saveBookAuthor.getBook().getTitle(), book.getTitle());
        Assertions.assertEquals(saveBookAuthor.getAuthor().getName(), author.getName());
    }
}