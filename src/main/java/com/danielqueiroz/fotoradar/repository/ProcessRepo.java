package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Process;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessRepo extends JpaRepository<Process, Long> {
    public Process findProcessByProcessNumber(String processNumber);
}
