package com.hospit.app.rest.Repo;

import com.hospit.app.rest.Models.Roles;
import com.hospit.app.rest.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByIdRol_IdRol(int idRol);
}
