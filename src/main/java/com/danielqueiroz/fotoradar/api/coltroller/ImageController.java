package com.danielqueiroz.fotoradar.api.coltroller;

import com.danielqueiroz.fotoradar.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/image")
@AllArgsConstructor
public class ImageController {

    private ImageService imageService;


    @GetMapping("")
    public ResponseEntity<?> findImages() {
        return ok().body(imageService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveImage(@RequestParam String link) {
        try {
            return ok().body(imageService.save(link));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
