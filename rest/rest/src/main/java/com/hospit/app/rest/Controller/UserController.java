package com.hospit.app.rest.Controller;


import com.hospit.app.rest.Models.User;
import com.hospit.app.rest.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getData")
    public ArrayList<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return this.userService.registerUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();

        if (userService.isValidEmail(email, password)) {
            return new ResponseEntity<>("Login realizado con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Credenciales incorrectas", HttpStatus.UNAUTHORIZED);
        }
    }

}
