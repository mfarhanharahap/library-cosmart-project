package com.example.librarycosmartproject.service;

import com.example.librarycosmartproject.common.DateUtil;
import com.example.librarycosmartproject.dto.TicketResponseDTO;
import com.example.librarycosmartproject.entity.Book;
import com.example.librarycosmartproject.entity.Ticket;
import com.example.librarycosmartproject.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final BookService bookService;
    private final AuthorService authorService;
    private final DateUtil dateUtil;

    public TicketResponseDTO bookSchedule(Integer bookId) {
        Book book = bookService.findById(bookId);

        Ticket ticket = new Ticket();
        ticket.setBook(book);
        ticket.setStartLoanDate(Optional.ofNullable(book.getLastLoanDate()).orElse(new Date()));
        ticket.setExpiredLoanDate(dateUtil.addDate(ticket.getStartLoanDate(), 7));
        Ticket saveTicket = ticketRepository.save(ticket);

        book.setAvailableToBorrow(Boolean.FALSE);
        book.setLastLoanDate(saveTicket.getExpiredLoanDate());
        book.setUpdateDate(new Date());
        bookService.saveBook(book);

        return convertTicketResponse(saveTicket);
    }

    private TicketResponseDTO convertTicketResponse(Ticket ticket) {
        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
        ticketResponseDTO.setTitle(ticket.getBook().getTitle());
        ticketResponseDTO.setEdition(ticket.getBook().getEditionNumber());
        ticketResponseDTO.setPickUpSchedule(ticket.getStartLoanDate());
        ticketResponseDTO.setExpiredDate(ticket.getExpiredLoanDate());
        ticketResponseDTO.setAuthors(authorService.getListAuthorByBookTitle(ticket.getBook().getTitle()));
        return ticketResponseDTO;
    }


}
