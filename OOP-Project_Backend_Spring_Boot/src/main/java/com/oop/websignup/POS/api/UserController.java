package com.oop.websignup.POS.api;

import com.oop.websignup.POS.dto.UserDTO;
import com.oop.websignup.POS.dto.UserDTOLogIn;
import com.oop.websignup.POS.service.UserService;
import com.oop.websignup.POS.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")  // Customize the origins if needed
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<StandardResponse> signUp(@Valid @RequestBody UserDTO dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new StandardResponse(400, "Validation Error", bindingResult.getAllErrors()),
                    HttpStatus.BAD_REQUEST);
        }
        String result = userService.signUpUser(dto);
        return new ResponseEntity<>(new StandardResponse(201, result + " saved", result),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<StandardResponse> logIn(@Valid @RequestBody UserDTOLogIn dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new StandardResponse(400, "Validation Error", bindingResult.getAllErrors()),
                    HttpStatus.BAD_REQUEST);
        }
        String result = userService.logInUser(dto);
        return new ResponseEntity<>(new StandardResponse(202, result, result),
                HttpStatus.ACCEPTED);
    }
}
