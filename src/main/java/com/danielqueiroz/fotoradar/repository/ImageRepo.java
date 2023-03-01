package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Image;
import com.danielqueiroz.fotoradar.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepo extends MongoRepository<Image, String> {

    Image findImageById(String id);
    Image findImageByLink(String link);

    Image findImageByLinkAndUser(String link, User user);

    List<Image> findImagesByUser(User user);
}
