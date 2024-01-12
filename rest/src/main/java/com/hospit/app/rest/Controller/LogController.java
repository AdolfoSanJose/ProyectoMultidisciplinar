package com.hospit.app.rest.Controller;

import com.hospit.app.rest.Models.Log;

import com.hospit.app.rest.Services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

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
