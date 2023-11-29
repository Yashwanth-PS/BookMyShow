package com.bookmyshow.controller;

import com.bookmyshow.dto.TicketRequestDTO;
import com.bookmyshow.dto.TicketResponseDTO;
import com.bookmyshow.exception.ShowSeatNotAvailableException;
import com.bookmyshow.exception.TicketNotFoundException;
import com.bookmyshow.model.Ticket;
import com.bookmyshow.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<TicketResponseDTO> createTicket(@RequestBody TicketRequestDTO bookTicketRequestDTO) throws ShowSeatAlreadyBookedException, ShowSeatNotAvailableException {
        Ticket ticket = ticketService.bookTicket(bookTicketRequestDTO.getUserId(), bookTicketRequestDTO.getShowSeatIds(), bookTicketRequestDTO.getShowId());

        TicketResponseDTO ticketResponse = new TicketResponseDTO();
        // Set ticketResponse properties based on the booked ticket

        return ResponseEntity.ok(ticketResponse);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long ticketId) throws TicketNotFoundException {
        Ticket ticket = ticketService.getTicket(ticketId);
        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/cancel/{ticketId}")
    public ResponseEntity<Ticket> cancelTicket(@PathVariable Long ticketId) {
        Ticket canceledTicket = ticketService.cancelTicket(ticketId);
        return ResponseEntity.ok(canceledTicket);
    }

    @PostMapping("/transfer/{ticketId}/{fromUserId}/{toUserId}")
    public ResponseEntity<Ticket> transferTicket(@PathVariable Long ticketId,
                                                 @PathVariable Long fromUserId,
                                                 @PathVariable Long toUserId) throws TicketNotFoundException, UserNotFoundException {
        Ticket transferredTicket = ticketService.transferTicket(ticketId, fromUserId, toUserId);
        return ResponseEntity.ok(transferredTicket);
    }

    // Additional methods as needed for your application

}