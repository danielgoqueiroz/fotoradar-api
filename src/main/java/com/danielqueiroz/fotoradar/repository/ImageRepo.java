package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Image;
import com.danielqueiroz.fotoradar.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
    Image findImageByLink(String link);
}
