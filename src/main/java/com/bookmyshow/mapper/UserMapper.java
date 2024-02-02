package com.bookmyshow.mapper;

import com.bookmyshow.dto.UserSignInResponseDTO;
import com.bookmyshow.dto.UserSignUpResponseDTO;
import com.bookmyshow.model.User;

public class UserMapper { // TODO: create a user object and send the details

    public static UserSignUpResponseDTO convertUserToUserSignUpResponseDTO(User user) {
        UserSignUpResponseDTO userSignUpResponseDTO = new UserSignUpResponseDTO();
        // userSignUpResponseDTO.setId(user.getId());
        userSignUpResponseDTO.setName(user.getName());
        userSignUpResponseDTO.setEmail(user.getEmail());
        // userSignUpResponseDTO.setTickets(user.getTickets());
        // userSignUpResponseDTO.setResponseCode(200);
        // userSignUpResponseDTO.setResponseMessage("SUCCESS");
        return userSignUpResponseDTO;
    }

    public static UserSignInResponseDTO convertUserToUserSignInResponseDTO(User user) {
        UserSignInResponseDTO userSignInResponseDTO = new UserSignInResponseDTO();
        userSignInResponseDTO.setName(user.getName());
        userSignInResponseDTO.setEmail(user.getEmail());
        return userSignInResponseDTO;
    }
}
