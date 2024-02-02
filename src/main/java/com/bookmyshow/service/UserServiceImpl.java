package com.bookmyshow.service;

import com.bookmyshow.dto.UserSignInRequestDTO;
import com.bookmyshow.dto.UserSignUpRequestDTO;
import com.bookmyshow.exception.InvalidCredentialException;
import com.bookmyshow.exception.UserAlreadyExistsException;
import com.bookmyshow.exception.UserNotFoundException;
import com.bookmyshow.model.User;
import com.bookmyshow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(UserSignInRequestDTO userSignInRequestDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(userSignInRequestDTO.getEmail());
        User user = optionalUser.orElseThrow(() -> new UserNotFoundException("User with given email does not exist: " +  userSignInRequestDTO.getEmail()));
        if (passwordEncoder.matches(user.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new InvalidCredentialException("Invalid credentials for user with email: " + userSignInRequestDTO.getEmail());
        }
    }
    @Override
    public User signUp(UserSignUpRequestDTO userSignUpRequestDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(userSignUpRequestDTO.getEmail());
        optionalUser.ifPresent(u -> {throw new UserAlreadyExistsException("User with given email already exists: " + email);});
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(userSignUpRequestDTO.getPassword()));
        newUser.setName(userSignUpRequestDTO.getName());
        newUser.setEmail(userSignUpRequestDTO.getEmail());
        return userRepository.save(newUser);
    }
}