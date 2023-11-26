package com.bookmyshow.dto;

import com.bookmyshow.model.Ticket;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserSignUpResponseDTO {
    private String name;
    private String email;
    private int responseCode;
    private String responseMessage;
}
