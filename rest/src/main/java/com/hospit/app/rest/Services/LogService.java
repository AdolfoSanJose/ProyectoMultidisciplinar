package com.hospit.app.rest.Services;

import com.hospit.app.rest.Models.Log;
import com.hospit.app.rest.Repo.LogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code LogService} class provides business logic for managing log entries.
 * It is annotated as a Spring service to indicate that it contains business logic.
 */
@Service
public class LogService {
    /**
     * The Spring Data repository for log entries.
     */
    @Autowired
    private LogRepo logRepo;

    /**
     * Retrieves a list of log entries based on the user's ID.
     *
     * @param idUser The ID of the user associated with the log entries.
     * @return A list of log entries associated with the specified user ID.
     */
    public List<Log> getLogsByUserId(int idUser) {
        return logRepo.findByIdUser_IdUser(idUser);
    }

    /**
     * Saves a log entry in the database.
     *
     * @param log The log entry to be saved.
     * @return The saved log entry.
     */
    public Log saveLog(Log log) {
        return logRepo.save(log);
    }
}