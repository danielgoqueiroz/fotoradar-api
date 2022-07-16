package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Page;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface NoticeRepo extends JpaRepository<Page, Long> {
    Page findNoticeById(Long id);
    List<Page> findAll();

}
