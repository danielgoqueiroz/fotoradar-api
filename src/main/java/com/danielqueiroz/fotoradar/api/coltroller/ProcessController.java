package com.danielqueiroz.fotoradar.api.coltroller;

import com.danielqueiroz.fotoradar.api.model.PageDTO;
import com.danielqueiroz.fotoradar.model.Page;
import com.danielqueiroz.fotoradar.model.Process;
import com.danielqueiroz.fotoradar.service.PageSevice;
import com.danielqueiroz.fotoradar.service.ProcessSevice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/process")
@CrossOrigin(origins = {"http://localhost:3000"})
public class ProcessController {

    private final ProcessSevice processService;

    public ProcessController(ProcessSevice processService) {
        this.processService = processService;
    }

    @GetMapping("")
    public ResponseEntity<?> getProcesses() {
        try {
            List<Process> processes = processService.getProcesses();
            return ok().body(processes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}


