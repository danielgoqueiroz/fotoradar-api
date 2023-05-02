package com.danielqueiroz.fotoradar.service;

import com.danielqueiroz.fotoradar.model.Image;
import com.danielqueiroz.fotoradar.model.Page;
import com.danielqueiroz.fotoradar.model.PageResponseDTO;
import com.danielqueiroz.fotoradar.model.User;
import com.danielqueiroz.fotoradar.repository.ImageRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
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
import java.util.*;

@Transactional
@Slf4j
@Service
@AllArgsConstructor
public class ImageService {

    private ImageRepo imageRepo;
    private UserService userService;
    private PageService pageService;
    private ImageSearchService imageSearchService;

    public Image saveImage(String imageUrl, String name) throws Exception {

        Image imageExample = Image.builder()
                .link(imageUrl)
                .user(userService.getCurrentUser())
                .build();
        Optional<Image> imageOpt = imageRepo.findOne(Example.of(imageExample));
        Image image = getImage(imageUrl, name, imageOpt);

        Set<PageResponseDTO> pages = getPageResponseDTOS(imageUrl);

        for (PageResponseDTO pageResponseDTO : pages) {
            Page page = pageService.addImageOnPageByResponseDTO(image, pageResponseDTO);
            log.info("Página adicionada: " + page);
        }

        return image;
    }

    private Image getImage(String imageUrl, String name, Optional<Image> imageOpt) throws Exception {
        Image image;
        if (imageOpt.isEmpty()) {
            StringBuffer content = new StringBuffer();
            try {
                getImage(imageUrl, content);
            } catch (MalformedURLException | ProtocolException e) {
                throw new Exception("Não foi possível carregar a imagem. Motivo: " + e.getMessage());
            }

            String encodedString = Base64.getEncoder().encodeToString(content.toString().getBytes());
            image = Image.builder()
                    .blob(encodedString)
                    .name(name)
                    .link(imageUrl)
                    .user(userService.getCurrentUser())
                    .build();

            image = imageRepo.save(image);
        } else {
            image = imageOpt.get();
        }
        return image;
    }

    private Set<PageResponseDTO> getPageResponseDTOS(String link) {
        Set<PageResponseDTO> pages = new HashSet<>();
        try {
            List<PageResponseDTO> bingResult = imageSearchService.bingSearch(link);
            pages.addAll(bingResult);
            List<PageResponseDTO> googleResult = imageSearchService.googleSearch(link);
            pages.addAll(googleResult);
        } catch (Exception e) {
            log.error("Não foi possível carregar a imagem. Motivo: " + e.getMessage());
        }
        return pages;
    }

    public Image findImageByLink(String link) {
        return imageRepo.findImageByLink(link);
    }

    ;

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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        User user = userService.getUser(username);

        return imageRepo.findImagesByUser(user);
    }

    public void delete(String id) {
        imageRepo.deleteById(id);
    }

    public Image findImage(String id) {
        User user = userService.getCurrentUser();

        Example<Image> example = Example.of(Image.builder()
                .id(id)
                .user(user)
                .build());
        return imageRepo.findOne(example)
                .orElseThrow(() -> new RuntimeException("Imagem não encontrada"));
    }
}
