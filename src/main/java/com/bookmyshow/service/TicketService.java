package com.bookmyshow.service;

import com.bookmyshow.exception.ShowSeatNotAvailableException;
import com.bookmyshow.model.Ticket;

import java.util.List;

public interface TicketService {
    Ticket bookTicket(Long userId, List<Long> showSeatIds, Long showId) throws ShowSeatNotAvailableException;

    Ticket getTicket(Long ticketId) throws TicketNotFoundException;

    Ticket cancelTicket(Long ticketId);

    Ticket transferTicket(Long ticketId, Long fromUserId, Long toUserId) throws TicketNotFoundException, UserNotFoundException;

}
