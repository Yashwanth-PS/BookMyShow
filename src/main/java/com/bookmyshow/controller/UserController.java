package com.bookmyshow.controller;

import com.bookmyshow.dto.UserSignInRequestDTO;
import com.bookmyshow.dto.UserSignInResponseDTO;
import com.bookmyshow.dto.UserSignUpRequestDTO;
import com.bookmyshow.dto.UserSignUpResponseDTO;
import com.bookmyshow.model.User;
import com.bookmyshow.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bookmyshow.controller.utils.ControllerUtil.validateUserSignUpRequestDTO;
import static com.bookmyshow.mapper.UserMapper.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponseDTO> signUp(@RequestBody UserSignUpRequestDTO userSignUpRequestDTO) {
        UserSignUpResponseDTO responseDTO;
        try {
            validateUserSignUpRequestDTO(userSignUpRequestDTO);
            User user = userService.signUp(userSignUpRequestDTO);
            responseDTO = convertUserToUserSignUpResponseDTO(user);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            log.error("Please enter Valid Email and Password Format");
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<UserSignInResponseDTO> signIn(@RequestBody UserSignInRequestDTO userSignInRequestDTO) {
        UserSignInResponseDTO responseDTO;
        try { // You may need to implement the actual sign-in logic based on your requirements
            User user = userService.login(userSignInRequestDTO);
            responseDTO = convertUserToUserSignInResponseDTO(user);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            log.error("Invalid Email and Password");
            return ResponseEntity.status(401).build(); // Unauthorized status code
        }
    }
}