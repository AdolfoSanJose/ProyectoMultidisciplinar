package com.hospit.app.rest.Repo;

import com.hospit.app.rest.Models.Messages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code MessageRepo} interface is a Spring Data repository for managing message entries in the database.
 * It extends {@link CrudRepository} to provide CRUD operations for the {@link Messages} entity.
 */
@Repository
public interface MessageRepo extends CrudRepository<Messages, Long> {

    /**
     * Retrieves a list of messages based on the sender's email address.
     *
     * @param remitenteEmail The email address of the sender.
     * @return A list of messages sent by the specified sender.
     */
    List<Messages> findByRemitente_Email(String remitenteEmail);
}