package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepo extends JpaRepository<Notice, Long> {
    Notice findFirstByLinkHash(String hash);
    Notice findNoticeById(Long id);
}
