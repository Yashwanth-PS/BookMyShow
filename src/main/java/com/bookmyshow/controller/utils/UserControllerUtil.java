package com.bookmyshow.controller.utils;

import com.bookmyshow.dto.UserSignUpRequestDTO;
import com.bookmyshow.dto.UserSignUpResponseDTO;
import com.bookmyshow.model.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class UserControllerUtil {
    /* public static void validateUserSignUpRequestDTO(UserSignUpRequestDTO userSignUpRequestDTO){
        // Validation Logic, If anything fails, throw an exception
        // Validate name
        if (userSignUpRequestDTO.getName() == null || userSignUpRequestDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (!userSignUpRequestDTO.getName().matches("[A-Za-z\\s]+")) {
            throw new IllegalArgumentException("Name can only contain letters and spaces");
        }

        // Validate email
        if (userSignUpRequestDTO.getEmail() == null || userSignUpRequestDTO.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (!userSignUpRequestDTO.getEmail().matches("^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        // Validate password
        if (userSignUpRequestDTO.getPassword() == null || userSignUpRequestDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if (userSignUpRequestDTO.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }

        if (!userSignUpRequestDTO.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*#?&])[\\w$@$!%*#?&]{8,}$")) {
            throw new IllegalArgumentException("Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character");
        }
    } */
    public static void validateUserSignUpRequestDTO(UserSignUpRequestDTO userSignUpRequestDTO) {
        // Validation Logic, If anything fails, throw an exception
        if (userSignUpRequestDTO == null) {
            throw new IllegalArgumentException("UserSignUpRequestDTO cannot be null");
        }

        // Using Bean Validation annotations
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserSignUpRequestDTO>> violations = validator.validate(userSignUpRequestDTO);

        if (!violations.isEmpty()) {
            // Collect and concatenate error messages
            StringBuilder errorMessage = new StringBuilder("Validation errors:\n");
            for (ConstraintViolation<UserSignUpRequestDTO> violation : violations) {
                errorMessage.append(violation.getPropertyPath()).append(": ").append(violation.getMessage()).append("\n");
            }
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }

    public static UserSignUpResponseDTO convertUserToUserResponseDTO(User user) {
        // TODO: create a user object and send the details
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
