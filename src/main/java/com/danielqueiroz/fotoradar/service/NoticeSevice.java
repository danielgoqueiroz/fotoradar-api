package com.danielqueiroz.fotoradar.service;

import com.danielqueiroz.fotoradar.exception.NoticeException;
import com.danielqueiroz.fotoradar.model.Company;
import com.danielqueiroz.fotoradar.model.Image;
import com.danielqueiroz.fotoradar.model.Notice;
import com.danielqueiroz.fotoradar.model.User;
import com.danielqueiroz.fotoradar.repository.CompanyRepo;
import com.danielqueiroz.fotoradar.repository.ImageRepo;
import com.danielqueiroz.fotoradar.repository.NoticeRepo;
import com.danielqueiroz.fotoradar.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.danielqueiroz.fotoradar.utils.Utils.getHash;

@Transactional
@Slf4j
@Service
@AllArgsConstructor
public class NoticeSevice {

    private final ImageRepo imageRepo;
    private final NoticeRepo noticeRepo;
    private final UserRepo userRepo;
    private final CompanyRepo companyRepo;

    public List<Notice> getLinks() {
        return noticeRepo.findAll();
    }

    public Notice getnoticeById(Long id) {
        return noticeRepo.findNoticeById(id);
    }

    public Notice save(String username, String url) throws NoticeException, MalformedURLException {
        String hash = getHash(url);
        Notice noticeOnDatabase = noticeRepo.findFirstByLinkHash(hash);
        if(noticeOnDatabase != null) {
           return null;
        }
        if (noticeOnDatabase.getCompany() == null) {
            String urlObject = new URL(url).getHost().toString();
            Company company = Company.builder()
                    .website(urlObject)
                    .build();
            Company companySaved = companyRepo.save(company);
            noticeOnDatabase.setCompany(companySaved);
        }
        User user = userRepo.findByUsername(username);

        Notice notice = Notice.builder()
                .user(user)
                .link(url)
                .linkHash(hash)
                .build();

        return noticeRepo.save(notice);
    }

    public void addImageOnNotice(Long idImage, Long idNotice) {
        Image image = imageRepo.getById(idImage);
        Notice notice = getnoticeById(idNotice);
        notice.setImage(image);
    }
}
