package com.hospit.app.rest.Services;

import com.hospit.app.rest.Models.Roles;
import com.hospit.app.rest.Models.User;
import com.hospit.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public ArrayList<User> getUsers(){
        return (ArrayList<User>)userRepo.findAll();
    }

    public User getUserDataByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public List<User> getUserDataByRoleId(int roleId) {
        return userRepo.findByIdRol_IdRol(roleId);
    }
    public User registerUser(User user){
        return userRepo.save(user);
    }

    public boolean isValidEmail(String email, String password) {
        Optional<User> userOptional = Optional.ofNullable(userRepo.findByEmail(email));
        return userOptional.isPresent() && userOptional.get().getPassword().equals(password);
    }
}
