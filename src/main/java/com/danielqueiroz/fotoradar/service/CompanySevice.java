package com.danielqueiroz.fotoradar.service;

import com.danielqueiroz.fotoradar.exception.NoticeException;
import com.danielqueiroz.fotoradar.model.Company;
import com.danielqueiroz.fotoradar.model.Image;
import com.danielqueiroz.fotoradar.model.Notice;
import com.danielqueiroz.fotoradar.model.User;
import com.danielqueiroz.fotoradar.repository.CompanyRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.danielqueiroz.fotoradar.utils.Utils.getHash;

@Transactional
@Slf4j
@Service
public class CompanySevice {

    private final CompanyRepo companyRepo;

    public CompanySevice(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    public List<Company> findCompanies() {
        return companyRepo.findAll();
    }

//    public Notice getnoticeById(Long id) {
//        return noticeRepo.findNoticeById(id);
//    }
//
//    public Notice save(String username, String url) throws NoticeException {
//        String hash = getHash(url);
//        Notice noticeOnDatabase = noticeRepo.findFirstByLinkHash(hash);
//        if(noticeOnDatabase != null) {
//           return null;
//        }
//        User user = userRepo.findByUsername(username);
//
//        Notice notice = Notice.builder()
//                .user(user)
//                .link(url)
//                .linkHash(hash)
//                .build();
//
//        return noticeRepo.save(notice);
//    }
//
//    public void addImageOnNotice(Long idImage, Long idNotice) {
//        Image image = imageRepo.getById(idImage);
//        Notice notice = getnoticeById(idNotice);
//        notice.setImage(image);
//    }
}
