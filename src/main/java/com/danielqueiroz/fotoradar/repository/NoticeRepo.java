package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoticeRepo extends MongoRepository<Page, String> {
    Page findNoticeById(String id);
    List<Page> findAll();

}
