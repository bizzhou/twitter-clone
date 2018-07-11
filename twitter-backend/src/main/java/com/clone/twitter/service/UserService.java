package com.clone.twitter.service;

import com.clone.twitter.repository.UserRepository;
import com.clone.twitter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Login function to check if email and password match with record in DB
     * @param email: user's login email
     * @param password: user's login password
     * @return user found in the database
     */
    @Transactional
    public User login(String email, String password) {
        // Check if user is in the database
        User user = userRepository.findUserByEmailAndPassword(email, password);

        // if user is not in the database, throw an exception
        if (user == null) {
            throw new ResponseStatusException(BAD_REQUEST, "email/password doesn't match");
        }
        return user;
    }

}
