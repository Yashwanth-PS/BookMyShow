package com.bookmyshow.service;

import com.bookmyshow.exception.ShowSeatNotAvailableException;
import com.bookmyshow.exception.TicketNotFoundException;
import com.bookmyshow.exception.UserNotFoundException;
import com.bookmyshow.model.*;
import com.bookmyshow.model.constants.ShowSeatStatus;
import com.bookmyshow.model.constants.TicketStatus;
import com.bookmyshow.repository.ShowRepository;
import com.bookmyshow.repository.ShowSeatRepository;
import com.bookmyshow.repository.TicketRepository;
import com.bookmyshow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ticket bookTicket(Long userId, List<Long> showSeatIds, Long showId) {
        User bookedByUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Show show = showRepository.findById(showId).orElseThrow(() -> new TicketNotFoundException("Show not found"));

        // Iterate over showSeatIds and lock the seats
        for (Long showSeatId : showSeatIds) {
            ShowSeat showSeat = showSeatRepository.findById(showSeatId).orElseThrow(() -> new ShowSeatNotAvailableException("Show Seat not available"));
            if (showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
                showSeatRepository.save(showSeat);
            } else {
                throw new ShowSeatNotAvailableException("Show Seat is not Available");
            }
        }

        boolean paymentDone = paymentCheck();
        List<ShowSeat> showSeats = new ArrayList<>();
        double amount = 0;
        if (paymentDone) {
            // Iterate over showSeatIds again to book the seats
            for (Long showSeatId : showSeatIds) {
                ShowSeat showSeat = showSeatRepository.findById(showSeatId).orElseThrow(() -> new ShowSeatNotAvailableException("Show Seat not available"));
                showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
                showSeat = showSeatRepository.save(showSeat);
                showSeats.add(showSeat);
                amount += showSeat.getPrice();
            }
        }
        return ticketGenerator(bookedByUser, show, showSeats, amount);
    }

    @Override
    public Ticket getTicket(Long ticketId) throws TicketNotFoundException {
        return ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
    }

    @Override
    public Ticket cancelTicket(Long ticketId) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        if(ticketOptional.isEmpty()){
            throw new TicketNotFoundException("Ticket for given ID is not found");
        }
        Ticket ticket = ticketOptional.get();
        ticket.setTicketStatus(TicketStatus.CANCELLED);
        for(ShowSeat showSeat: ticket.getShowSeats()){
            showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
            showSeatRepository.save(showSeat);
        }
        ticketRepository.save(ticket);
        /*
        for (Payment p : ticket.getPayments()) {
            p.getRefNo();
            // send a message to 3rd party with payment reference number for refund
        } */
        return ticket;
    }

    @Override
    public Ticket transferTicket(Long ticketId, Long fromUserId, Long toUserId) { /*
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        if(ticketOptional.isEmpty()){
            throw new TicketNotFoundException("Ticket for given ID is not found");
        }
        Optional<User> fromUserOptional = userRepository.findById(fromUserId);
        Optional<User> toUserOptional = userRepository.findById(toUserId);
        if(fromUserOptional.isEmpty() || toUserOptional.isEmpty()){
            throw new UserNotFoundException("User details given for ticket transfer is not found");
        }
        Ticket ticket = ticketOptional.get();

        User fromUser = fromUserOptional.get();
        List<Ticket> bookedTicketHistory = fromUser.getTickets();
        bookedTicketHistory.remove(ticket);
        userRepository.save(fromUser);

        User toUser = toUserOptional.get();
        bookedTicketHistory = toUser.getTickets();

        bookedTicketHistory.add(ticket);
        toUser = userRepository.save(toUser);

        ticket.setUser(toUser);
        return ticketRepository.save(ticket); */
        return null;
    }

    private boolean paymentCheck(){
        return true;
    }

    private Ticket ticketGenerator(User user, Show show, List<ShowSeat> showSeats, double amount) {
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setShow(show);
        ticket.setShowSeats(showSeats);
        ticket.setTotalAmount(amount);
        ticket.setTimeOfBooking(LocalDateTime.now());
        ticket.setTicketStatus(TicketStatus.BOOKED);
        return ticketRepository.save(ticket);
    }
}