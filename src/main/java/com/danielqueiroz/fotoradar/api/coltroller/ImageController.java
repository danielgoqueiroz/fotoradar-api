package com.danielqueiroz.fotoradar.api.coltroller;

import com.danielqueiroz.fotoradar.api.model.ErrorMessage;
import com.danielqueiroz.fotoradar.api.model.ImageDTO;
import com.danielqueiroz.fotoradar.service.ImageService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/image")
@AllArgsConstructor
@CrossOrigin(origins = {"*"})
@Log4j2
public class ImageController {

    private ImageService imageService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> findImages()
    {
        log.info("Find all images");
        return ok().body(imageService.findAll());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/find-by-id")
    public ResponseEntity<?> findImage(@RequestParam String id) {
        log.info("Find image by id: " + id);
        return ok().body(imageService.findImage(id));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveImage(@RequestBody ImageDTO image) {
        log.info("Save image: " + image);
        try {
            return ok().body(imageService.saveImage(image.getLink(), image.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ErrorMessage.builder()
                        .message(e.getMessage())
                        .className(e.getClass().getName())
                            .build()
            );
        }
    }

    @DeleteMapping(value = "" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteImage(@RequestParam String id) {
        log.info("Delete image: " + id);
        try {

            imageService.delete(id);
            return ok().build();
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
