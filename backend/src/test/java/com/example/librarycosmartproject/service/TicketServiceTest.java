package com.example.librarycosmartproject.service;

import com.example.librarycosmartproject.common.DateUtil;
import com.example.librarycosmartproject.dto.TicketResponseDTO;
import com.example.librarycosmartproject.entity.Book;
import com.example.librarycosmartproject.entity.Ticket;
import com.example.librarycosmartproject.repository.TicketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Ticket Service Unit test")
class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private BookService bookService;

    @Mock
    private AuthorService authorService;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private DateUtil dateUtil;

    @Test
    void bookSchedule() {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Hikayat Cinta");

        Ticket ticket = new Ticket();
        ticket.setBook(book);

        Mockito.when(bookService.findById(1)).thenReturn(book);
        Mockito.when(ticketRepository.save(Mockito.any(Ticket.class))).thenReturn(ticket);
        Mockito.when(bookService.saveBook(Mockito.any(Book.class))).thenReturn(book);
        Mockito.when(authorService.getListAuthorByBookTitle(Mockito.anyString())).thenReturn(List.of("Farhan"));

        TicketResponseDTO ticketResponseDTO = ticketService.bookSchedule(1);
        Assertions.assertEquals(ticketResponseDTO.getTitle(), book.getTitle());
    }
}