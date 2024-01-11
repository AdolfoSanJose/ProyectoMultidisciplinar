package com.hospit.app.rest.Repo;


import com.hospit.app.rest.Models.Log;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface LogRepo extends CrudRepository<Log, Integer> {
    List<Log> findByIdUser_IdUser(int idUser);

}