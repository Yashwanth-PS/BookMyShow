**Dependencies Used**

1) Spring Web
2) Lombok
3) Spring Data JPA
4) MySQL Driver

**Models**
- BaseModel
- Actor
- MovieFeature
- Movie - Actor
- SeatType
- SeatStatus
- Seat
- ShowSeatStatus
- ShowSeat - Show, Seat
- Show - Movie, Auditorium, ShowSeat
- AuditoriumFeature
- Auditorium - Show, Seat
- TicketStatus
- Ticket - Show, Show Seats
- Theatre - Auditorium
- City - Theatre
- PaymentMethod
- Payment - Ticket
- User - Ticket

**Repository**

1) MovieRepository
2) SeatRepository
3) ShowRepository
4) ShowSeatRepository
5) TicketRepository
6) UserRepository