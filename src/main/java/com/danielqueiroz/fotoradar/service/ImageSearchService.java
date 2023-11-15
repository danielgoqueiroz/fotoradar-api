package com.danielqueiroz.fotoradar.service;

import com.danielqueiroz.fotoradar.model.PageResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ImageSearchService {

    @Value("${REVERSE_SEARCH_KEY}")
    private String key;

    @Value("${reverse-search-api}")
    private String BASE_URL;

    public ImageSearchService() {}

    private List<PageResponseDTO> findPagesByImagelink(String imageUrl, String searchType) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("key", key);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<List<PageResponseDTO>> response = restTemplate.exchange(
                BASE_URL + searchType + "?imageURL=" + imageUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();

    }

    public List<PageResponseDTO> bingSearch(String imageUrl) {
        return findPagesByImagelink(imageUrl, "bingsearch");
    }

    public List<PageResponseDTO> googleSearch(String imageUrl) {
        return findPagesByImagelink(imageUrl, "googlesearch");
    }

    public List<PageResponseDTO> searchImage(String imageUrl) {
        List<PageResponseDTO> pageResult = new ArrayList<>();
        List<PageResponseDTO> pageResponseDTOSBing = bingSearch(imageUrl);
        List<PageResponseDTO> pageResponseDTOSGoogle = googleSearch(imageUrl);
        pageResult.addAll(pageResponseDTOSBing);
        pageResult.addAll(pageResponseDTOSGoogle);
        return pageResult;
    }
}
