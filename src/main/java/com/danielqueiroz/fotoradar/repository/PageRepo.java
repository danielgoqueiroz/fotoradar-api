package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Page;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PageRepo extends MongoRepository<Page, String> {
    Page findPageById(String id);

    List<Page> findAll();

}
