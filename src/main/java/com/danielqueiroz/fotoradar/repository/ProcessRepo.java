package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Process;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProcessRepo extends MongoRepository<Process, String> {
    public Process findProcessByProcessNumber(String processNumber);

}
