package com.bookmyshow.dto;

import com.bookmyshow.model.ShowSeat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TicketResponseDTO {
    private double totalAmount;
    private LocalDateTime showTime;
    private List<String> seatNumbers;
    private String movieName;
    private String auditoriumName;
}