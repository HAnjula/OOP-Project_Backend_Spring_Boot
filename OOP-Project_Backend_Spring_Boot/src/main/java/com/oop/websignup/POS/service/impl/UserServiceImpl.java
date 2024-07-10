package com.oop.websignup.POS.service.impl;


import com.oop.websignup.POS.dto.UserDTO;
import com.oop.websignup.POS.dto.UserDTOLogIn;
import com.oop.websignup.POS.entity.User;
import com.oop.websignup.POS.repo.UserRepo;
import com.oop.websignup.POS.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
            return "Email already exists!";
        }
        User user = new User(dto.getEmail(), dto.getName(), dto.getContactNumber(), dto.getPassword());
        return repo.save(user).getName();
    }

    @Override
    public String logInUser(UserDTOLogIn dto) {
        Optional<User> e = repo.findById(dto.getEmail());
        if (e.isPresent()){
            if (Objects.equals(dto.getPassword(),e.get().getPassword() )){
                return "Successfully logged!";
            }else {
                return "Wrong Password, Try Again!";
            }
        }else {
            return "Email not found!";
        }
    }
}
