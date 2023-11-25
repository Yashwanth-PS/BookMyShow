package com.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignInResponseDTO {
    // private Long id;
    private String name;
    private String email;
    // private List<Ticket> tickets; // Assuming tickets as an example property for the response
    private int responseCode;
    private String responseMessage;
}
