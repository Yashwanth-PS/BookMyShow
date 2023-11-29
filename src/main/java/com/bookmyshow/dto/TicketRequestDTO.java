package com.bookmyshow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequestDTO {
    private long userId;
    private List<Long> showSeatIds;
    private long showId;
}