package com.hospit.app.rest.Repo;

import com.hospit.app.rest.Models.Messages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends CrudRepository<Messages, Long> {
    List<Messages> findByRemitente_Email(String remitenteEmail);
}