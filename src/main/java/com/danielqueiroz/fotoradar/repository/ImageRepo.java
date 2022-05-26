package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Image;
import com.danielqueiroz.fotoradar.model.Role;
import com.danielqueiroz.fotoradar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepo extends JpaRepository<Image, Long> {
    Image findImageByLink(String link);

    Image findImageByLinkAndUser(String link, User user);

    List<Image> findImagesByUser(User user);
}
