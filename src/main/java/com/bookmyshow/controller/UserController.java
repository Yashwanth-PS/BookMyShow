package com.bookmyshow.controller;

import com.bookmyshow.model.User;
import com.bookmyshow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponseDTO> signUp(@RequestBody UserSignUpRequestDTO userSignUpRequestDTO) {
        UserSignUpResponseDTO responseDTO = new UserSignUpResponseDTO();

        try {
            UserControllerUtil.validateUserSignUpRequestDTO(userSignUpRequestDTO);
            User user = userService.signUp(
                    userSignUpRequestDTO.getName(),
                    userSignUpRequestDTO.getEmail(),
                    userSignUpRequestDTO.getPassword()
            );

            responseDTO.setId(user.getId());
            responseDTO.setName(user.getName());
            responseDTO.setEmail(user.getEmail());
            responseDTO.setTickets(user.getTickets());
            responseDTO.setResponseCode(200);
            responseDTO.setResponseMessage("SUCCESS");

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setResponseCode(500);
            responseDTO.setResponseMessage("Internal Server Error");
            return ResponseEntity.status(500).body(responseDTO);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<UserSignInResponseDTO> signIn(@RequestBody UserSignInRequestDTO userSignInRequestDTO) {
        UserSignInResponseDTO responseDTO = new UserSignInResponseDTO();

        try { // You may need to implement the actual sign-in logic based on your requirements
            User user = userService.login(userSignInRequestDTO.getEmail(),
                                          userSignInRequestDTO.getPassword());

            // responseDTO.setId(user.getId());
            responseDTO.setName(user.getName());
            responseDTO.setEmail(user.getEmail());
            // responseDTO.setTickets(user.getTickets());
            responseDTO.setResponseCode(200);
            responseDTO.setResponseMessage("SUCCESS");

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            // responseDTO.setResponseCode(401); // Unauthorized status code
            // responseDTO.setResponseMessage("Invalid credentials");
            return ResponseEntity.status(401).body(responseDTO);
        }
    }
}