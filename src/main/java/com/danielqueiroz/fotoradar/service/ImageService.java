package com.danielqueiroz.fotoradar.service;

import com.danielqueiroz.fotoradar.exception.ImageExceptions;
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Base64.getEncoder;
import static org.springframework.data.domain.Example.of;

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
        User currentUser = userService.getCurrentUser();
        Image imageExample = Image.builder()
                .link(imageUrl)
                .user(currentUser)
                .build();
        Optional<Image> imageOpt = imageRepo.findOne(of(imageExample));

        if (imageOpt.isEmpty()) {
            try {
                byte[] imageContent = getImageBytes(imageUrl);
                String encodedString = getEncoder().encodeToString(imageContent);
                Image image = Image.builder()
                        .blob(encodedString)
                        .name(name)
                        .link(imageUrl)
                        .user(currentUser)
                        .build();
                return imageRepo.save(image);
            } catch (MalformedURLException | ProtocolException e) {
                throw new Exception("Não foi possível carregar a imagem. Motivo: " + e.getMessage());
            }
        } else {
            return imageOpt.get();
        }
    }

    private void addPagePagesUsage(String imageUrl, Image image) {
        Set<PageResponseDTO> pages = getPageResponseDTOS(imageUrl);
        for (PageResponseDTO pageResponseDTO : pages) {
            Page page = pageService.addImageOnPageByResponseDTO(image, pageResponseDTO);
            log.info("Página adicionada: " + page);
        }
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

    private byte[] getImageBytes(String link) throws IOException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .build();

        try {
            HttpResponse<byte[]> response  = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new IOException("Failed to download image. Status code: " + response.statusCode());
            }
        } catch (IOException e) {
            throw new ImageExceptions("Erro ao obter conteúdo de imagem", e);
        } catch (InterruptedException e) {
            throw new ImageExceptions("Interrupt error", e);
        }

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

        Example<Image> example = of(Image.builder()
                .id(id)
                .user(user)
                .build());
        return imageRepo.findOne(example)
                .orElseThrow(() -> new RuntimeException("Imagem não encontrada"));
    }
}
