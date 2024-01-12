package com.hospit.app.rest.Services;

import com.hospit.app.rest.Models.Roles;
import com.hospit.app.rest.Models.User;
import com.hospit.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code UserService} class provides business logic for managing user entries.
 * It is annotated as a Spring service to indicate that it contains business logic.
 */
@Service
public class UserService {
    /**
     * The Spring Data repository for user entries.
     */
    @Autowired
    private UserRepo userRepo;

    /**
     * Retrieves a list of all users in the database.
     *
     * @return A list of all users.
     */
    public ArrayList<User> getUsers() {
        return (ArrayList<User>) userRepo.findAll();
    }

    /**
     * Retrieves user data based on the email address.
     *
     * @param email The email address of the user.
     * @return The user associated with the specified email address.
     */
    public User getUserDataByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    /**
     * Retrieves a list of users based on the role ID.
     *
     * @param roleId The ID of the role associated with the users.
     * @return A list of users associated with the specified role ID.
     */
    public List<User> getUserDataByRoleId(int roleId) {
        return userRepo.findByIdRol_IdRol(roleId);
    }

    /**
     * Registers a new user in the database.
     *
     * @param user The user to be registered.
     * @return The registered user.
     */
    public User registerUser(User user) {
        return userRepo.save(user);
    }

    /**
     * Validates the user's email and password combination.
     *
     * @param email    The email address of the user.
     * @param password The password of the user.
     * @return {@code true} if the email and password combination is valid, {@code false} otherwise.
     */
    public boolean isValidEmail(String email, String password) {
        Optional<User> userOptional = Optional.ofNullable(userRepo.findByEmail(email));
        return userOptional.isPresent() && userOptional.get().getPassword().equals(password);
    }
}