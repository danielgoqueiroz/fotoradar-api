package com.danielqueiroz.fotoradar.api.coltroller;

import com.danielqueiroz.fotoradar.api.model.ErrorMessage;
import com.danielqueiroz.fotoradar.api.model.ImageDTO;
import com.danielqueiroz.fotoradar.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/image")
@AllArgsConstructor
public class ImageController {

    private ImageService imageService;

//    @PreAuthorize("hasRole('USER')")
    @GetMapping("")
    public ResponseEntity<?> findImages() {
        return ok().body(imageService.findAll());
    }

//    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveImage(@RequestBody ImageDTO image) {
        try {
            return ok().body(imageService.save(image.getLink(), image.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ErrorMessage.builder()
                        .message(e.getMessage())
                        .className(e.getClass().getName())
                            .build()
            );
        }
    }

}
