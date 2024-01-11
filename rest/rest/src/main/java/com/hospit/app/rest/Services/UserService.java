package com.hospit.app.rest.Services;

import com.hospit.app.rest.Models.User;
import com.hospit.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public ArrayList<User> getUsers(){
        return (ArrayList<User>)userRepo.findAll();
    }

    public User registerUser(User user){
        return userRepo.save(user);
    }

    public boolean isValidEmail(String email, String password) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        return userOptional.isPresent() && userOptional.get().getPassword().equals(password);
    }
}
