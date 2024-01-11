package com.hospit.app.rest.Services;

import com.hospit.app.rest.Models.Log;
import com.hospit.app.rest.Repo.LogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogRepo logRepo;

    public List<Log> getLogsByUserId(int idUser) {
        return logRepo.findByIdUser_IdUser(idUser);
    }

    public Log saveLog(Log log) {
        return logRepo.save(log);
    }
}
