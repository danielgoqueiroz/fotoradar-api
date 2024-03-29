package com.danielqueiroz.fotoradar.api;

import com.danielqueiroz.fotoradar.contants.Const;
import com.danielqueiroz.fotoradar.model.User;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static com.danielqueiroz.fotoradar.contants.Const.BEARER;
import static java.lang.String.valueOf;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

@Component
public class TestUtils {

    public static String getToken(Integer port) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> vars = new LinkedMultiValueMap<>();
        vars.add("username", "daniel");
        vars.add("password", "senha123");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(vars, headers);

        ResponseEntity<String> stringResponseEntity = restTemplate.exchange("http://localhost:"+port+"/api/login",
                POST,
                entity,
                String.class);
        JSONObject jsonBody = new JSONObject(stringResponseEntity.getBody());
        String token = jsonBody.get("access_token").toString();
        return BEARER + token;
    }

    public static User createUser() {
        User user = new User();
        user.setUsername("daniel");
        user.setPassword("senha123");
        return user;
    }

    public static byte[] toJson(User user) throws JSONException {
        return new JSONObject(valueOf(user)).toString().getBytes();
    }

    public static String toGson(Object object) {
        return new Gson().toJson(object);
    }
}
