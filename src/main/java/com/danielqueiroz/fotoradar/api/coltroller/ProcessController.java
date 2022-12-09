package com.danielqueiroz.fotoradar.api.coltroller;

import com.danielqueiroz.fotoradar.model.Process;
import com.danielqueiroz.fotoradar.service.ProcessSevice;
import org.apache.tomcat.jni.Proc;
import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

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

    @GetMapping("/find-by-id")
    public ResponseEntity<?> getProcessById(@RequestParam String id) {
        try {
            Process processes = processService.getProcess(id);
            return ok().body(processes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("")
    public ResponseEntity<?> updateProcessNumber(@RequestParam String id, @RequestParam String processNumber) {
        try {
            Process processes = processService.updateProcessNumber(id, processNumber);
            return ok().body(processes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/add-payment")
    public ResponseEntity<?> addPayment(@RequestParam String id, @RequestParam String value, @RequestParam String date) {
        try {
            Process processes = processService.addPayment(id, value, date);
            return ok().body(processes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}


