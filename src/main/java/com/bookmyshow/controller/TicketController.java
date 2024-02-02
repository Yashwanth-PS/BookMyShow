package com.bookmyshow.controller;

import com.bookmyshow.dto.TicketRequestDTO;
import com.bookmyshow.dto.TicketResponseDTO;
import com.bookmyshow.exception.TicketNotFoundException;
import com.bookmyshow.exception.UserNotFoundException;
import com.bookmyshow.model.Ticket;
import com.bookmyshow.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bookmyshow.controller.utils.ControllerUtil.validateTicketId;
import static com.bookmyshow.mapper.TicketMapper.convertTicketToTicketResponseDTO;

@Slf4j
@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/book")
    public ResponseEntity<TicketResponseDTO> bookTicket(@RequestBody TicketRequestDTO bookTicketRequestDTO) {
        try {
            Ticket ticket = ticketService.bookTicket(bookTicketRequestDTO.getUserId(), bookTicketRequestDTO.getShowSeatIds(), bookTicketRequestDTO.getShowId());
            TicketResponseDTO ticketResponse = convertTicketToTicketResponseDTO(ticket);
            return ResponseEntity.ok(ticketResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponseDTO> getTicket(@PathVariable Long ticketId) {
        if (validateTicketId(ticketId)) {
            log.error("Invalid ticketId: {}", ticketId);
            return ResponseEntity.badRequest().build();
        }
        try {
            Ticket ticket = ticketService.getTicket(ticketId);
            TicketResponseDTO ticketResponse = convertTicketToTicketResponseDTO(ticket);
            return ResponseEntity.ok(ticketResponse);
        } catch (TicketNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/cancel/{ticketId}")
    public ResponseEntity<TicketResponseDTO> cancelTicket(@PathVariable Long ticketId) {
        Ticket canceledTicket = ticketService.cancelTicket(ticketId);
        TicketResponseDTO ticketResponse = convertTicketToTicketResponseDTO(canceledTicket);
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