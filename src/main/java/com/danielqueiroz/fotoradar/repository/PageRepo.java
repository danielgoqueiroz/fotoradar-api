package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Page;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepo extends MongoRepository<Page, String> {
    Page findPageById(String id);

    List<Page> findAll();

}
