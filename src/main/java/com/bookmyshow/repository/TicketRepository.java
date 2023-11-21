package com.bookmyshow.repository;

import com.bookmyshow.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // Optional is like a placeholder, It might have the object, or it might be Empty
    // If the data is not found, It wouldn't be null instead it will be Empty
    @Override
    Optional<Ticket> findById(Long ticketId);
    @Override
    Ticket save(Ticket ticket);
}