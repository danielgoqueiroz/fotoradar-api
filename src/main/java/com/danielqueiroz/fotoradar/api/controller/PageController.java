package com.danielqueiroz.fotoradar.api.controller;

import com.danielqueiroz.fotoradar.model.Page;
import com.danielqueiroz.fotoradar.service.PageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/page")
@CrossOrigin(origins = {"*"})
@Log4j2
public class PageController {

    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping("")
    public ResponseEntity<?> getPages() {
        log.info("Find all pages");
        try {
            List<Page> pages = pageService.getPages();
            return ok().body(pages);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> getPageById(@RequestParam String id) {
        log.info("Find page by id: " + id);
        try {
            Page page = pageService.getPageById(id);
            return ok().body(page);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/find-by-image-id")
    public ResponseEntity<?> getPagesByImageId(@RequestParam String id) {
        log.info("Find pages by image id: " + id);
        try {
            List<Page> pages = pageService.getPagesByImgageId(id);
            return ok().body(pages);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/add-image-on-page")
    public ResponseEntity<?> addUsageToImageByImageId(@RequestParam String url, @RequestParam String imageId) {
        log.info("Add image on page: " + imageId);
        pageService.addImageOnPageWithUrl(imageId, url);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePage(@RequestBody Page page) {
        log.info("Update page: " + page);
        Page pageUpdated = pageService.updatePage(page);
        return ResponseEntity.ok(pageUpdated);
    }

    @PutMapping(value = "process", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePageProcess(@RequestParam String pageId, @RequestParam String processNumber) {
        log.info("Update page: " + pageId);
        Page pageUpdated = pageService.updatePageProcess(pageId, processNumber);
        return ResponseEntity.ok(pageUpdated);
    }

}


