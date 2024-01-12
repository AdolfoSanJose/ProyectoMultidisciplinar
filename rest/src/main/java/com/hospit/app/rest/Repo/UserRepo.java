package com.hospit.app.rest.Repo;

import com.hospit.app.rest.Models.Roles;
import com.hospit.app.rest.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code UserRepo} interface is a Spring Data repository for managing user entries in the database.
 * It extends {@link CrudRepository} to provide CRUD operations for the {@link User} entity.
 */
@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    /**
     * Retrieves a user based on the email address.
     *
     * @param email The email address of the user.
     * @return The user associated with the specified email address.
     */
    User findByEmail(String email);

    /**
     * Retrieves a list of users based on the role ID.
     *
     * @param idRol The ID of the role associated with the users.
     * @return A list of users associated with the specified role ID.
     */
    List<User> findByIdRol_IdRol(int idRol);
}
