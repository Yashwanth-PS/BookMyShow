package com.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignInResponseDTO {
    private String name;
    private String email;
    private int responseCode;
    private String responseMessage;
}
