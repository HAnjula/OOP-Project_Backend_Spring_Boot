package com.oop.websignup.POS.service.impl;

import com.oop.websignup.POS.dto.UserDTO;
import com.oop.websignup.POS.dto.UserDTOLogIn;
import com.oop.websignup.POS.entity.User;
import com.oop.websignup.POS.exception.InvalidPassExc;
import com.oop.websignup.POS.exception.NotFoundExc;
import com.oop.websignup.POS.exception.UserExistsExc;
import com.oop.websignup.POS.repo.UserRepo;
import com.oop.websignup.POS.service.UserService;
import com.oop.websignup.POS.util.PasswordHash;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo repo;

    public UserServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public String signUpUser(UserDTO dto) {
        if (repo.existsById(dto.getEmail())) {
            throw new UserExistsExc("Email already exists!");
        }

        // Hash the password before saving
        String hashedPassword = PasswordHash.hashPassword(dto.getPassword());
        User user = new User(dto.getEmail(), dto.getName(), dto.getContactNumber(), hashedPassword);
        return repo.save(user).getName();
    }

    @Override
    public String logInUser(UserDTOLogIn dto) {
        Optional<User> e = repo.findById(dto.getEmail());
        if (e.isPresent()) {
            User user = e.get();
            // Check the password using PasswordUtils
            if (PasswordHash.checkPassword(dto.getPassword(), user.getPassword())) {
                return "Successfully logged in!";
            } else {
                throw new InvalidPassExc("Wrong Password, Try Again!");
            }
        } else {
            throw new NotFoundExc("Email not found!");
        }
    }
}
