package com.bookmyshow.service;

import com.bookmyshow.exception.InvalidCredentialException;
import com.bookmyshow.exception.UserAlreadyExistsException;
import com.bookmyshow.model.User;
import com.bookmyshow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        User user = optionalUser.orElseThrow(() -> new UserNotFoundException("User with given email does not exist: " + email));

        if (Objects.equals(user.getPassword(), password)) {
            return user;
        } else {
            throw new InvalidCredentialException("Invalid credentials for user with email: " + email);
        }
    }

    @Override
    public User signUp(String name, String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        optionalUser.ifPresent(u -> {
            throw new UserAlreadyExistsException("User with given email already exists: " + email);
        });

        User newUser = new User();
        newUser.setPassword(password);
        newUser.setName(name);
        newUser.setEmail(email);

        return userRepository.save(newUser);
    }
}