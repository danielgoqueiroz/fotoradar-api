package com.danielqueiroz.fotoradar.api.coltroller;

import com.danielqueiroz.fotoradar.api.model.NoticeRequestDTO;
import com.danielqueiroz.fotoradar.model.Notice;
import com.danielqueiroz.fotoradar.service.NoticeSevice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/notice")
@CrossOrigin(origins = {"http://localhost:3000"})
public class NoticeController {

    private final NoticeSevice noticeService;

    public NoticeController(NoticeSevice noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("")
    public ResponseEntity<?> getNotices() {

        try {
            List<Notice> notices = noticeService.getNotices();
            return ok().body(notices);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUsage(@RequestBody NoticeRequestDTO noticesLinks) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();

        List<Notice> notices = new ArrayList<>();
        List<String> erros = new ArrayList<>();

        noticesLinks.getLinks().forEach(link -> {
            try {
                notices.add(noticeService.save(username, link, noticesLinks.getImageId()));
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
    public ResponseEntity<?> addImageOnUsage(@RequestParam Long idNotice, @RequestParam Long idImage) {
        noticeService.addImageOnNotice(idImage, idNotice);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateNotice(@RequestBody Notice notice) {
        Notice noticeUpdated = noticeService.updateNotice(notice);
        return ResponseEntity.ok(noticeUpdated);
    }

    @PutMapping(value = "process", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateNoticeProcess(@RequestParam String noticeId, @RequestParam String processNumber) {
        Notice noticeUpdated = noticeService.updateNoticeProcess(Long.valueOf(noticeId), processNumber);
        return ResponseEntity.ok(noticeUpdated);
    }

    @PostMapping("add-payment")
    public ResponseEntity<?> addPayment(@RequestParam String idNotice, @RequestParam String value) {

        BigDecimal valueDecimal = BigDecimal.valueOf(Long.valueOf(value));
        noticeService.addPayment(Long.valueOf(idNotice), valueDecimal);
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


