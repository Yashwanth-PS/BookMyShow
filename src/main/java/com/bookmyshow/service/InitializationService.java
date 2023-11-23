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
public class InitializationService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

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

    private void initializeCity() {
        for(int id = 1; id <= 10; id++) {
            City city = new City();
            city.setName("Sample City" + id);
            cityRepository.save(city);
        }
    }

    private void initializeTheatre() {
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

    public void  initializeActor(){
        for(int id = 1; id <= 10; id++){
            Actor actor = new Actor();
            actor.setName("Sample Actor" + id);
            actorRepository.save(actor);
        }
    }

    private void initializeMovie() {
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

    private void initializeAuditorium() {
        for(long id = 1; id <= 10; id++) {
            Auditorium auditorium = new Auditorium();
            auditorium.setName("Sample Auditorium" + id);
            auditorium.setCapacity(100);
            auditorium.setTheatre(theatreRepository.findById(id).get());
            auditoriumRepository.save(auditorium);
        }
    }

    private void initializeSeat() {
        for(int id = 1; id <= 100; id++) {
            Seat seat = new Seat();
            seat.setSeatNumber(""+id);
            seat.setSeatType(SeatType.GOLD);
            seat.setSeatStatus(SeatStatus.AVAILABLE);
            seatRepository.save(seat);
        }
    }

    private void initializeShow() {
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

    private void initializeShowSeat() {
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



    private void initializeUser() {
        for(int id = 1; id <= 10; id++) {
            User user = new User();
            user.setName("SampleUser" + id);
            user.setEmail("user" + id +"@example.com");
            userRepository.save(user);
        }
    }

    private void initializeTicket() {
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
    private void initializePayment() {
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