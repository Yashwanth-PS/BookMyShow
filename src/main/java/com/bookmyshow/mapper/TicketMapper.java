package com.bookmyshow.mapper;

import com.bookmyshow.dto.TicketResponseDTO;
import com.bookmyshow.model.Ticket;

import java.util.List;
import java.util.stream.Collectors;

public class TicketMapper {
    public static TicketResponseDTO convertTicketToTicketResponseDTO(Ticket ticket) {
        TicketResponseDTO ticketResponse = new TicketResponseDTO();
        // Set ticketResponse properties based on the booked ticket
        ticketResponse.setShowTime(ticket.getShow().getStartTime());
        ticketResponse.setMovieName(ticket.getShow().getMovie().getName());
        ticketResponse.setTotalAmount(ticket.getTotalAmount());
        ticketResponse.setAuditoriumName(ticket.getShow().getAuditorium().getName());
        /* List<String> seatNumber = new ArrayList<>();
        for (ShowSeat seat : ticket.getShowSeats()) {
            seatNumber.add(seat.getSeat().getSeatNumber());
        } */
        List<String> seatNumber = ticket.getShowSeats().stream()
                .map(showSeat -> showSeat.getSeat().getSeatNumber())
                .collect(Collectors.toList());
        ticketResponse.setSeatNumbers(seatNumber);
        return ticketResponse;
    }
}
