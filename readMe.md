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

1) ActorRepository
2) AuditoriumRepository
3) CityRepository
4) MovieRepository
5) PaymentRepository
6) SeatRepository
7) ShowRepository
8) ShowSeatRepository
9) TheatreRepository
10) TicketRepository
11) UserRepository

**DTOs**

1) UserSignInRequestDTO
2) UserSignInResponseDTO
3) UserSignUpRequestDTO
4) UserSignUpResponseDTO

**Service**

1) Initialisation Service
2) UserService