package com.hospit.app.rest.Controller;

import com.hospit.app.rest.Models.Log;

import com.hospit.app.rest.Services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code LogController} class is a Spring MVC controller that handles logging-related requests.
 * It provides endpoints for retrieving logs by user ID and saving new log entries.
 */
@RestController
@CrossOrigin
@RequestMapping("/log")
public class LogController {

    /**
     * The service responsible for handling log-related operations.
     */
    @Autowired
    private LogService logService;

    /**
     * Handles the HTTP POST request to retrieve logs based on a specified user ID.
     *
     * @param requestBody A {@code Map} containing the request body with the "idUser" parameter.
     * @return A {@code ResponseEntity} containing a list of logs if successful, or an error response otherwise.
     */
    @PostMapping("/getLogsByUserId")
    public ResponseEntity<List<Log>> getLogsByUserId(@RequestBody Map<String, Integer> requestBody) {
        Integer idUser = requestBody.get("idUser");

        if (idUser != null) {
            List<Log> logsData = logService.getLogsByUserId(idUser);

            if (!logsData.isEmpty()) {
                return ResponseEntity.ok(logsData);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Handles the HTTP POST request to save a new log entry.
     *
     * @param log The {@code Log} object representing the log entry to be saved.
     * @return A {@code ResponseEntity} containing the saved log if successful, or an error response otherwise.
     */
    @PostMapping("/saveLog")
    public ResponseEntity<Log> saveLog(@RequestBody Log log) {
        Log savedLog = logService.saveLog(log);

        if (savedLog != null) {
            return ResponseEntity.ok(savedLog);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
