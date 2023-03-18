package com.danielqueiroz.fotoradar.api.coltroller;

import com.danielqueiroz.fotoradar.api.model.CompanyDTO;
import com.danielqueiroz.fotoradar.model.Company;
import com.danielqueiroz.fotoradar.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/company")
@AllArgsConstructor
@CrossOrigin(origins = "*")
@Log4j2
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("")
    public ResponseEntity<List<Company>> getCompanies() {
        return ok().body(companyService.findCompanies());
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<Company> getCompany(@RequestParam String id) {
        log.info("Find company by id: " + id);
        return ok().body(companyService.findCompany(id));
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> updateCompany(@RequestBody CompanyDTO company) {
        log.info("Update company: " + company);
        return ok().body(companyService.updateCompany(company));
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


