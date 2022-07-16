package com.danielqueiroz.fotoradar.api.coltroller;

import com.danielqueiroz.fotoradar.api.model.PageDTO;
import com.danielqueiroz.fotoradar.model.Page;
import com.danielqueiroz.fotoradar.service.PageSevice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/page")
@CrossOrigin(origins = {"http://localhost:3000"})
public class PageController {

    private final PageSevice pageService;

    public PageController(PageSevice pageService) {
        this.pageService = pageService;
    }

    @GetMapping("")
    public ResponseEntity<?> getPages() {

        try {
            List<Page> pages = pageService.getPages();
            return ok().body(pages);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUsage(@RequestBody PageDTO pagessLinks) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();

        List<Page> pages = new ArrayList<>();
        List<String> erros = new ArrayList<>();

        pagessLinks.getLinks().forEach(link -> {
            try {
                pages.add(pageService.save(username, link, pagessLinks.getImageId()));
            } catch (Exception e) {
                erros.add(e.getMessage());
            }
        });
        if (erros.isEmpty()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body(erros);
        }

    }

    @PostMapping("/add-image-on-notice")
    public ResponseEntity<?> addImageOnUsage(@RequestParam Long idPage, @RequestParam Long idImage) {
        pageService.addImageOnPage(idImage, idPage);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateNotice(@RequestBody Page page) {
        Page pageUpdated = pageService.updatePage(page);
        return ResponseEntity.ok(pageUpdated);
    }

    @PutMapping(value = "process", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePageProcess(@RequestParam String noticeId, @RequestParam String processNumber) {
        Page pageUpdated = pageService.updatePageProcess(Long.valueOf(noticeId), processNumber);
        return ResponseEntity.ok(pageUpdated);
    }

    @PostMapping("add-payment")
    public ResponseEntity<?> addPayment(@RequestParam String idNotice, @RequestParam String value) {

        BigDecimal valueDecimal = BigDecimal.valueOf(Long.valueOf(value));
        pageService.addPayment(Long.valueOf(idNotice), valueDecimal);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/links")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<?> saveLinks(@RequestBody List<String> links) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username =  (String) auth.getPrincipal();
//
//        List<Notice> linksProcessed = links.stream().map(link -> {
//            try {
//                return noticeService.save(username, link, imageId);
//            } catch (NoticeException e) {
//                throw new RuntimeException(e.getMessage());
//            } catch (MalformedURLException e) {
//                throw new RuntimeException(e);
//            }
//        }).collect(Collectors.toList());
//
//        List<Notice> linksIgnored = linksProcessed.stream().filter(l -> Objects.isNull(l)).collect(Collectors.toList());
//        List<Notice> linksSaved = linksProcessed.stream().filter(l -> Objects.nonNull(l)).collect(Collectors.toList());
//        if (linksSaved.isEmpty()) {
//            return ResponseEntity.badRequest().body(
//                    "Nenhum registro salvo"
//            );
//        } else {
//            return ResponseEntity.ok().body(
//                    String.format("%s registros ignorados. %s salvos com sucesso",linksIgnored.size(),  linksSaved.size())
//            );
//        }
//    }

}


