package com.example.librarycosmartproject.repository;

import com.example.librarycosmartproject.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
