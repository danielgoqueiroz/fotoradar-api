package com.danielqueiroz.fotoradar.service;

import com.danielqueiroz.fotoradar.exception.NoticeException;
import com.danielqueiroz.fotoradar.model.Notice;
import com.danielqueiroz.fotoradar.model.User;
import com.danielqueiroz.fotoradar.repository.NoticeRepo;
import com.danielqueiroz.fotoradar.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.danielqueiroz.fotoradar.utils.Utils.getHash;

@Transactional
@Slf4j
@Service
public class NoticeSevice {

    private final NoticeRepo noticeRepo;
    private final UserRepo userRepo;

    public NoticeSevice(NoticeRepo usageRepo, UserRepo userRepo) {
        this.noticeRepo = usageRepo;
        this.userRepo = userRepo;
    }

    public List<Notice> getLinks() {
        return noticeRepo.findAll();
    }

    public Notice getnoticeById(String id) {
        return noticeRepo.findNoticeById(id);
    }

    public Notice save(String username, String url) throws NoticeException {
        String hash = getHash(url);
        Notice noticeOnDatabase = noticeRepo.findFirstByLinkHash(hash);
        if(noticeOnDatabase != null) {
           return null;
        }
        User user = userRepo.findByUsername(username);

        Notice notice = Notice.builder()
                .user(user)
                .link(url)
                .linkHash(hash)
                .build();

        return noticeRepo.save(notice);
    }

    public void addImageOnNotice(String idImage, String idNotice) {
        Notice notice = getnoticeById(idNotice);
    }
}
