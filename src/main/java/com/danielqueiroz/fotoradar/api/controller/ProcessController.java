package com.danielqueiroz.fotoradar.api.controller;

import com.danielqueiroz.fotoradar.model.Process;
import com.danielqueiroz.fotoradar.service.ProcessSevice;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/process")
@CrossOrigin(origins = {"*"})
@Log4j2
public class ProcessController {

    private final ProcessSevice processService;

    public ProcessController(ProcessSevice processService) {
        this.processService = processService;
    }

    @GetMapping("")
    public ResponseEntity<?> getProcesses() {
        log.info("Find all processes");
        try {
            List<Process> processes = processService.getProcesses();
            return ok().body(processes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> getProcessById(@RequestParam String id) {
        log.info("Find process by id: " + id);
        try {
            Process processes = processService.getProcess(id);
            return ok().body(processes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("")
    public ResponseEntity<?> updateProcessNumber(@RequestParam String id, @RequestParam String processNumber) {
        log.info("Update process number: " + processNumber);
        try {
            Process processes = processService.updateProcessNumber(id, processNumber);
            return ok().body(processes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/add-payment")
    public ResponseEntity<?> addPayment(@RequestParam String id, @RequestParam String value, @RequestParam String date) {
        log.info("Add payment: " + value);
        try {
            Process processes = processService.addPayment(id, value, date);
            return ok().body(processes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}


