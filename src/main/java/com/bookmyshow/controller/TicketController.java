package com.bookmyshow.controller;

import com.bookmyshow.dto.TicketRequestDTO;
import com.bookmyshow.dto.TicketResponseDTO;
import com.bookmyshow.exception.ShowSeatAlreadyBookedException;
import com.bookmyshow.exception.ShowSeatNotAvailableException;
import com.bookmyshow.exception.TicketNotFoundException;
import com.bookmyshow.exception.UserNotFoundException;
import com.bookmyshow.model.ShowSeat;
import com.bookmyshow.model.Ticket;
import com.bookmyshow.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        ticketResponse.setTimeOfShow(ticket.getShow().getStartTime());
        ticketResponse.setMovieName(ticket.getShow().getMovie().getName());
        ticketResponse.setTotalAmount(ticket.getTotalAmount());
        ticketResponse.setAuditoriumName(ticket.getShow().getAuditorium().getName());
        List<String> seatNumber = new ArrayList<>();
        for(ShowSeat seat : ticket.getShowSeats()) {
            seatNumber.add(seat.getSeat().getSeatNumber());
        }
        ticketResponse.setSeatNumbers(seatNumber);
        return ResponseEntity.ok(ticketResponse);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponseDTO> getTicket(@PathVariable Long ticketId) throws TicketNotFoundException {
        Ticket ticket = ticketService.getTicket(ticketId);
        TicketResponseDTO ticketResponse = new TicketResponseDTO();
        // Set ticketResponse properties based on the booked ticket
        ticketResponse.setTimeOfShow(ticket.getShow().getStartTime());
        ticketResponse.setMovieName(ticket.getShow().getMovie().getName());
        ticketResponse.setTotalAmount(ticket.getTotalAmount());
        ticketResponse.setAuditoriumName(ticket.getShow().getAuditorium().getName());
        List<String> seatNumber = new ArrayList<>();
        for(ShowSeat seat : ticket.getShowSeats()) {
            seatNumber.add(seat.getSeat().getSeatNumber());
        }
        ticketResponse.setSeatNumbers(seatNumber);
        return ResponseEntity.ok(ticketResponse);
    }

    @PostMapping("/cancel/{ticketId}")
    public ResponseEntity<TicketResponseDTO> cancelTicket(@PathVariable Long ticketId) {
        Ticket canceledTicket = ticketService.cancelTicket(ticketId);
        TicketResponseDTO ticketResponse = new TicketResponseDTO();
        // Set ticketResponse properties based on the booked ticket
        ticketResponse.setTimeOfShow(canceledTicket.getShow().getStartTime());
        ticketResponse.setMovieName(canceledTicket.getShow().getMovie().getName());
        ticketResponse.setTotalAmount(canceledTicket.getTotalAmount());
        ticketResponse.setAuditoriumName(canceledTicket.getShow().getAuditorium().getName());
        List<String> seatNumber = new ArrayList<>();
        for(ShowSeat seat : canceledTicket.getShowSeats()) {
            seatNumber.add(seat.getSeat().getSeatNumber());
        }
        ticketResponse.setSeatNumbers(seatNumber);
        return ResponseEntity.ok(ticketResponse);
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

/* Book Ticket --> Post - http://localhost:8181/api/ticket/book
{
  "userId": 1,
  "showSeatIds": [1, 2, 3],
  "showId": 1
}

Get Ticket --> GET - http://localhost:8181/api/ticket/11

Cancel Ticket --> POST - http://localhost:8181/api/ticket/cancel/11

Transfer Ticket --> POST - http://localhost:8181/api/ticket/transfer/10/10/9
*/