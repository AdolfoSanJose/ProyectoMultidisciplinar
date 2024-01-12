package com.hospit.app.rest.Repo;


import com.hospit.app.rest.Models.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code LogRepo} interface is a Spring Data repository for managing log entries in the database.
 * It extends {@link CrudRepository} to provide CRUD operations for the {@link Log} entity.
 */
@Repository
public interface LogRepo extends CrudRepository<Log, Integer> {

    /**
     * Retrieves a list of log entries based on the user's ID.
     *
     * @param idUser The ID of the user associated with the log entries.
     * @return A list of log entries associated with the specified user ID.
     */
    List<Log> findByIdUser_IdUser(int idUser);
}