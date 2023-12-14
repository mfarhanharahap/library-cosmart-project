package com.example.librarycosmartproject.controller;

import com.example.librarycosmartproject.dto.BookDTO;
import com.example.librarycosmartproject.dto.TicketResponseDTO;
import com.example.librarycosmartproject.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/schedule")
    public ResponseEntity<TicketResponseDTO> getAllBooksBySubject(Integer bookId){
        return ResponseEntity.ok(ticketService.bookSchedule(bookId));
    }
}
