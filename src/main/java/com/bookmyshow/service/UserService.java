package com.bookmyshow.service;

import com.bookmyshow.dto.UserSignInRequestDTO;
import com.bookmyshow.dto.UserSignUpRequestDTO;
import com.bookmyshow.model.User;

public interface UserService {
    User login(UserSignInRequestDTO userSignInRequestDTO);
    User signUp(UserSignUpRequestDTO userSignUpRequestDTO);
}
