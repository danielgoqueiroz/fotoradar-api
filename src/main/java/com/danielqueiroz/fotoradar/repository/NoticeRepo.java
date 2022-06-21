package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Notice;
import com.danielqueiroz.fotoradar.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface NoticeRepo extends JpaRepository<Notice, Long> {
    Notice findFirstByLinkHash(String hash);
    Notice findNoticeById(Long id);
    List<Notice> findAll();

    @Query("SELECT n FROM notice n WHERE n.user_id = 2")
    Collection<Notice> findNoticeByUserId(Long userId);
}
