package com.oop.websignup.POS.service;

import com.oop.websignup.POS.dto.UserDTO;
import com.oop.websignup.POS.dto.UserDTOLogIn;

public interface UserService {
    String signUpUser(UserDTO dto);
    String logInUser(UserDTOLogIn dto);
}
