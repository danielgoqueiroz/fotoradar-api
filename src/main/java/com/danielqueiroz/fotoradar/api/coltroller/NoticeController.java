package com.danielqueiroz.fotoradar.api.coltroller;

import com.danielqueiroz.fotoradar.exception.NoticeException;
import com.danielqueiroz.fotoradar.model.Notice;
import com.danielqueiroz.fotoradar.service.NoticeSevice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    private final NoticeSevice noticeService;

    public NoticeController(NoticeSevice noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("")
    public ResponseEntity<List<Notice>> getLinks() {
        return ok().body(noticeService.getLinks());
    }

    @PostMapping("")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> saveUsage(@RequestParam String link) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username =  (String) auth.getPrincipal();

        Notice usageSaved = null;
        try {
            usageSaved = noticeService.save(username, link);
        } catch (NoticeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-image-on-notice")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addImageOnUsage(@RequestParam Long idNotice, @RequestParam Long idImage) {
        noticeService.addImageOnNotice(idImage, idNotice);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/links")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> saveLinks(@RequestBody List<String> links) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username =  (String) auth.getPrincipal();

        List<Notice> linksProcessed = links.stream().map(link -> {
            try {
                return noticeService.save(username, link);
            } catch (NoticeException e) {
                throw new RuntimeException(e.getMessage());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        List<Notice> linksIgnored = linksProcessed.stream().filter(l -> Objects.isNull(l)).collect(Collectors.toList());
        List<Notice> linksSaved = linksProcessed.stream().filter(l -> Objects.nonNull(l)).collect(Collectors.toList());
        if (linksSaved.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    "Nenhum registro salvo"
            );
        } else {
            return ResponseEntity.ok().body(
                    String.format("%s registros ignorados. %s salvos com sucesso",linksIgnored.size(),  linksSaved.size())
            );
        }
    }

}


