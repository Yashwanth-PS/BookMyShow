package com.bookmyshow.model;

import com.bookmyshow.model.constants.TicketStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Ticket extends BaseModel{
    private LocalDateTime timeOfBooking;
    private double totalAmount;
    @ManyToMany
    private List<ShowSeat> showSeats;
    @ManyToOne
    private Show show;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;
}

/* Suppose, Ticket1 bought seat1 then Ticket1 was cancelled,
then Ticket2 bought seat1 then Ticket2 was cancelled
then Ticket3 bought seat1
Seat 1 Belongs to 3 tickets, 2 Cancelled and 1 Active

Similarly, Ticket4 bought Seat2, Seat3, Seat4
1 Ticket can have many ShowSeats */

/* Ticket : Show
      1   :   1
      M   :   1

Ticket : ShowSeat
   1   :    M
   M   :    1

2 Way Mapping
User -> Ticket
Ticket -> User

1 Way Mapping
User -> Ticket */