package com.danielqueiroz.fotoradar.api.coltroller;

import com.danielqueiroz.fotoradar.api.TestUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.danielqueiroz.fotoradar.api.TestUtils.getToken;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    Integer randomServerPort;

    @Test
    void doLogin() throws Exception {
            mockMvc.perform(post("/api/login")
                        .contentType(APPLICATION_FORM_URLENCODED_VALUE)
                        .param("username", "daniel")
                        .param("password", "senha123"))
                .andExpect(content().string(containsString("access_token")));
    }

    @Test
    void getTokenValue() throws JSONException {
        String token = getToken(randomServerPort);
        Assertions.assertNotNull(token);
        Assertions.assertTrue(token != null);
        Assertions.assertEquals(203, token.length());
    }

    @Test
    void getUsers() throws Exception {
        mockMvc.perform(get("/api/user")
                .header(AUTHORIZATION, getToken(randomServerPort)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string("{\"name\":\"da\",\"username\":\"daniel\",\"email\":\"a2@asd\",\"cpf\":null,\"images\":[]}"));

    }

    @Test
    void saveUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void saveRole() {
    }

    @Test
    void addToUser() {
    }
}