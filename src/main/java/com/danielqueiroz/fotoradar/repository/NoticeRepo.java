package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepo extends JpaRepository<Notice, Long> {
    Notice findFirstByLinkHash(String hash);
    Notice findNoticeById(Long id);
    List<Notice> findAll();
}
