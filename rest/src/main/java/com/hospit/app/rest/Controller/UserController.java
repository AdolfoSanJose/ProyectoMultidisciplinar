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

/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code UserController} class is a Spring MVC controller that handles user-related requests.
 * It provides endpoints for retrieving user data, registering new users, and handling user authentication.
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    /**
     * The service responsible for handling user-related operations.
     */
    @Autowired
    UserService userService;

    /**
     * Handles the HTTP GET request to retrieve a list of all users' data.
     *
     * @return An {@code ArrayList} of {@code User} objects containing users' data.
     */
    @GetMapping("/getData")
    public ArrayList<User> getUsers(){
        return userService.getUsers();
    }

    /**
     * Handles the HTTP POST request to retrieve user data based on the specified email.
     *
     * @param requestBody A {@code Map} containing the request body with the "email" parameter.
     * @return A {@code ResponseEntity} containing the user data if successful, or an error response otherwise.
     */
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

    /**
     * Handles the HTTP POST request to retrieve user data based on the specified role.
     *
     * @param role The {@code Roles} object containing the role information.
     * @return A {@code ResponseEntity} containing a list of users with the specified role if successful,
     *         or an error response otherwise.
     */
    @PostMapping("/getUserDataByRole")
    public ResponseEntity<List<User>> getUserDataByRole(@RequestBody Roles role) {
        List<User> userData = userService.getUserDataByRoleId(role.getIdRol());

        if (!userData.isEmpty()) {
            return ResponseEntity.ok(userData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Handles the HTTP POST request to register a new user.
     *
     * @param user The {@code User} object representing the user to be registered.
     * @return The registered {@code User} object.
     */
    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return this.userService.registerUser(user);
    }

    /**
     * Handles the HTTP POST request for user login authentication.
     *
     * @param user The {@code User} object containing the email and password for authentication.
     * @return A {@code ResponseEntity} with a success message if authentication is successful,
     *         or an error response otherwise.
     */
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
