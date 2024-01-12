package com.hospit.app.rest.Controller;


import com.hospit.app.rest.Models.Roles;
import com.hospit.app.rest.Models.User;
import com.hospit.app.rest.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getData")
    public ArrayList<User> getUsers(){
        return userService.getUsers();
    }
    @PostMapping("/getUserDataByEmail")
    public ResponseEntity<User> getUserDataByEmail(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");

        if (email != null) {
            User userData = userService.getUserDataByEmail(email);

            if (userData != null) {
                return ResponseEntity.ok(userData);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/getUserDataByRole")
    public ResponseEntity<List<User>> getUserDataByRole(@RequestBody Roles role) {
        List<User> userData = userService.getUserDataByRoleId(role.getIdRol());

        if (!userData.isEmpty()) {
            return ResponseEntity.ok(userData);
        } else {
            return ResponseEntity.notFound().build();
        }
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
