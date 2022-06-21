package com.danielqueiroz.fotoradar.api.coltroller;

import com.danielqueiroz.fotoradar.api.model.CompanyDTO;
import com.danielqueiroz.fotoradar.exception.NoticeException;
import com.danielqueiroz.fotoradar.model.Company;
import com.danielqueiroz.fotoradar.model.Notice;
import com.danielqueiroz.fotoradar.service.CompanySevice;
import com.danielqueiroz.fotoradar.service.NoticeSevice;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/company")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CompanyController {

    private final CompanySevice companySevice;

    @GetMapping("")
    public ResponseEntity<List<Company>> getCompanies() {
        return ok().body(companySevice.findCompanies());
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> updateCompany(@RequestBody CompanyDTO company) {
        return ok().body(companySevice.updateCompany(company));
    }

//    @PostMapping("")
////    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<?> saveUsage(@RequestParam String link) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username =  (String) auth.getPrincipal();
//
//        Notice usageSaved = null;
//        try {
//            usageSaved = noticeService.save(username, link);
//        } catch (NoticeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/add-image-on-notice")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<?> addImageOnUsage(@RequestParam Long idNotice, @RequestParam Long idImage) {
//        noticeService.addImageOnNotice(idImage, idNotice);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/links")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<?> saveLinks(@RequestBody List<String> links) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username =  (String) auth.getPrincipal();
//
//        List<Notice> linksProcessed = links.stream().map(link -> {
//            try {
//                return noticeService.save(username, link);
//            } catch (NoticeException e) {
//                throw new RuntimeException(e.getMessage());
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


