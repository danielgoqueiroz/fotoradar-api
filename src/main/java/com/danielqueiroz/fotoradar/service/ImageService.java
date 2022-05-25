package com.danielqueiroz.fotoradar.service;

import com.danielqueiroz.fotoradar.exception.AlreadyExistException;
import com.danielqueiroz.fotoradar.model.Image;
import com.danielqueiroz.fotoradar.model.User;
import com.danielqueiroz.fotoradar.repository.ImageRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Transactional
@Slf4j
@Service
@AllArgsConstructor
public class ImageService {

    private ImageRepo imageRepo;
    private UserService userService;

    public Image save(String link, String name) throws Exception {
        boolean hasImage = !Objects.isNull(imageRepo.findImageByLink(link));
        if (hasImage) {
            throw new AlreadyExistException("Imagem já salva") ;
        }
        StringBuffer content = new StringBuffer();
        try {
            getImage(link, content);
        } catch (MalformedURLException | ProtocolException e) {
            throw new Exception("Não foi possível carregar a imagem. Motivo: " + e.getMessage());
        }

        String encodedString = Base64.getEncoder().encodeToString(content.toString().getBytes());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username =  (String) auth.getPrincipal();

        User user = userService.getUser(username);
        Image image = Image.builder()
                .blob(encodedString)
                .name(name)
                .link(link)
                .user(user)
                .build();
        return imageRepo.save(image);
    }

    public Image findImageByLink(String link) {
        return imageRepo.findImageByLink(link);
    };
    private void getImage(String link, StringBuffer content) throws IOException {
        URL url = new URL(link);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
    }

    public List<Image> findAll() {
        return imageRepo.findAll();
    }
}
