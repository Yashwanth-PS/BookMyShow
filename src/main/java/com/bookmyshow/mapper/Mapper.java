package com.bookmyshow.mapper;

import com.bookmyshow.dto.UserSignUpResponseDTO;
import com.bookmyshow.model.User;

public class Mapper { // TODO: create a user object and send the details
    public static UserSignUpResponseDTO convertUserToUserResponseDTO(User user) {
        UserSignUpResponseDTO userSignUpResponseDTO = new UserSignUpResponseDTO();
        // userSignUpResponseDTO.setId(user.getId());
        userSignUpResponseDTO.setName(user.getName());
        userSignUpResponseDTO.setEmail(user.getEmail());
        // userSignUpResponseDTO.setTickets(user.getTickets());
        userSignUpResponseDTO.setResponseCode(200);
        userSignUpResponseDTO.setResponseMessage("SUCCESS");
        return userSignUpResponseDTO;
    }
}
