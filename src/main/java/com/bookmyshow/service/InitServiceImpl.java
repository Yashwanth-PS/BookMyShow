package com.bookmyshow.service;

import com.bookmyshow.model.*;
import com.bookmyshow.model.constants.*;
import com.bookmyshow.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@Service
public class InitServiceImpl implements InitService{
    private final CityRepository cityRepository;
    private final TheatreRepository theatreRepository;
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final ShowRepository showRepository;
    private final AuditoriumRepository auditoriumRepository;
    private final SeatRepository seatRepository;
    private final ShowSeatRepository showSeatRepository;
    private final PaymentRepository paymentRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    @Autowired
    public InitServiceImpl(CityRepository cityRepository, TheatreRepository theatreRepository, MovieRepository movieRepository, ActorRepository actorRepository, ShowRepository showRepository, AuditoriumRepository auditoriumRepository, SeatRepository seatRepository, ShowSeatRepository showSeatRepository, PaymentRepository paymentRepository, TicketRepository ticketRepository, UserRepository userRepository) {
        this.cityRepository = cityRepository;
        this.theatreRepository = theatreRepository;
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.showRepository = showRepository;
        this.auditoriumRepository = auditoriumRepository;
        this.seatRepository = seatRepository;
        this.showSeatRepository = showSeatRepository;
        this.paymentRepository = paymentRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct // Indicates that it is initialization method
    public void initializeData() {
        initializeCity();
        initializeTheatre();
        initializeActor();
        initializeMovie();
        initializeAuditorium();
        initializeSeat();
        initializeShow();
        initializeShowSeat();
        initializeUser();
        initializeTicket();
        initializePayment();
    }

    public void initializeCity() {
        for(int id = 1; id <= 10; id++) {
            City city = new City();
            city.setName("Sample City" + id);
            cityRepository.save(city);
        }
    }

    public void initializeTheatre() {
        for (long id = 1; id <= 10; id++) {
            Optional<City> optionalCity = cityRepository.findById(id);
            if (optionalCity.isPresent()) {
                City city = optionalCity.get();
                Theatre theatre = new Theatre();
                theatre.setName("Sample Theatre");
                theatre.setAddress("Sample Address");
                theatre.setCity(city);
                theatreRepository.save(theatre);
            } else { // Handle the case where City with the ID is not found
                System.out.println("City with ID " + id + " not found!"); // You may log a warning or throw an exception, depending on your requirements.
            } // For simplicity, I'm just logging a message here.
        }
    }

    public void initializeActor(){
        for(int id = 1; id <= 10; id++){
            Actor actor = new Actor();
            actor.setName("Sample Actor" + id);
            actorRepository.save(actor);
        }
    }

    public void initializeMovie() {
        for(long id = 1; id <= 10; id++) {
            Optional<Actor> optionalActor = actorRepository.findById(id);
            Movie movie = new Movie();
            movie.setName("Sample Movie" + id);
            movie.setDescription("Sample Description for Movie" + id);
            movie.setLanguage("English");
            movie.setActors(Arrays.asList(optionalActor.get()));
            movieRepository.save(movie);
        }
    }

    public void initializeAuditorium() {
        for(long id = 1; id <= 10; id++) {
            Auditorium auditorium = new Auditorium();
            auditorium.setName("Sample Auditorium" + id);
            auditorium.setCapacity(100);
            auditorium.setTheatre(theatreRepository.findById(id).get());
            auditoriumRepository.save(auditorium);
        }
    }

    public void initializeSeat() {
        for(int id = 1; id <= 100; id++) {
            Seat seat = new Seat();
            seat.setSeatNumber(""+id);
            seat.setSeatType(SeatType.GOLD);
            seat.setSeatStatus(SeatStatus.AVAILABLE);
            seatRepository.save(seat);
        }
    }

    public void initializeShow() {
        for(long id = 1; id <= 10; id++) {
            Optional<Movie> movie = movieRepository.findById(id);
            Optional<Auditorium> auditorium = auditoriumRepository.findById(id);
            Show show = new Show();
            show.setStartTime(LocalDateTime.now());
            show.setEndTime(LocalDateTime.now().plusHours(2));
            show.setMovie(movie.get());
            show.setAuditorium(auditorium.get());
            showRepository.save(show);
        }
    }

    public void initializeShowSeat() {
        for(long id = 1; id <= 10; id++) {
            Optional<Show> show = showRepository.findById(id);
            Optional<Seat> seat = seatRepository.findById(id);
            ShowSeat showSeat = new ShowSeat();
            showSeat.setPrice(100);
            showSeat.setShow(show.get());
            showSeat.setSeat(seat.get());
            showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
            showSeatRepository.save(showSeat);
        }
    }

    public void initializeUser() {
        for(int id = 1; id <= 10; id++) {
            User user = new User();
            user.setName("SampleUser" + id);
            user.setEmail("user" + id +"@example.com");
            user.setPassword("user" + id +"pass");
            userRepository.save(user);
        }
    }

    public void initializeTicket() {
        for(long id = 1; id <= 10; id++) {
            Optional<ShowSeat> showSeat = showSeatRepository.findById(id);
            Optional<Show> show = showRepository.findById(id);
            Ticket ticket = new Ticket();
            ticket.setTimeOfBooking(LocalDateTime.now());
            ticket.setTotalAmount(100);
            ticket.setShowSeats(Arrays.asList(showSeat.get()));
            ticket.setShow(show.get());
            ticket.setTicketStatus(TicketStatus.BOOKED);
            ticket.setUser(userRepository.findById(id).get());
            ticketRepository.save(ticket);
        }
    }
    public void initializePayment() {
        for(long id = 1; id <= 10; id++) {
            Optional<ShowSeat> showSeat = showSeatRepository.findById(id);
            Payment payment = new Payment();
            payment.setPaymentTime(LocalDateTime.now());
            payment.setAmount(100);
            payment.setReferenceId("REF-"+id);
            payment.setShowSeatStatus(ShowSeatStatus.BOOKED);
            payment.setPaymentMethod(PaymentMethod.CREDIT_CARD);
            payment.setTicket(ticketRepository.findById(id).get());
            paymentRepository.save(payment);
        }
    }
}
