package com.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignInRequestDTO {
    private String email;
    private String password;
}
