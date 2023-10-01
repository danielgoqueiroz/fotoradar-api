package com.danielqueiroz.fotoradar.api.coltroller;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.danielqueiroz.fotoradar.api.TestUtils.getToken;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
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
                .andExpect(content().string(containsString("access_token")))
                    .andExpect(status().isOk());

    }

    @Test
    void registerUser() throws Exception {
        mockMvc.perform(post("/api/user/save")
                        .contentType(APPLICATION_FORM_URLENCODED_VALUE)
                        .param("username", "daniel")
                        .param("password", "senha123"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string("{\"name\":null,\"username\":\"daniel\",\"email\":null,\"cpf\":null,\"images\":[]}"));

    }

    @Test
    void registerUserWithToken() throws Exception {
        mockMvc.perform(post("/api/user/save")
                        .header(AUTHORIZATION, getToken(randomServerPort))
                        .contentType(APPLICATION_FORM_URLENCODED_VALUE)
                        .param("username", "daniel")
                        .param("password", "senha123"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string("{\"name\":null,\"username\":\"daniel\",\"email\":null,\"cpf\":null,\"images\":[]}"));

    }

    @Test
    void registerUserWithToken2() throws Exception {
        mockMvc.perform(post("/api/user/save")
                        .header(AUTHORIZATION, getToken(randomServerPort))
                        .contentType(APPLICATION_FORM_URLENCODED_VALUE)
                        .param("username", "daniel")
                        .param("password", "senha123"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string("{\"name\":null,\"username\":\"daniel\",\"email\":null,\"cpf\":null,\"images\":[]}"));

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
    @Transactional
    void updateUser() throws Exception {
        mockMvc.perform(put("/api/user")
                        .header(AUTHORIZATION, getToken(randomServerPort)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string("{\"name\":\"da\",\"username\":\"daniel\",\"email\":\"a2@asd\",\"cpf\":null,\"images\":[]}"));

    }

    @Test
    void saveRole() throws Exception{
    }

    @Test
    void addToUser() throws Exception{
    }
}