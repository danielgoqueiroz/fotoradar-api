package com.danielqueiroz.fotoradar.api.controller;

import com.danielqueiroz.fotoradar.api.model.CreateUserDTO;
import com.danielqueiroz.fotoradar.model.User;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static com.danielqueiroz.fotoradar.api.TestUtils.getToken;
import static com.danielqueiroz.fotoradar.api.TestUtils.toGson;
import static com.danielqueiroz.fotoradar.stub.CreateUserDTOStub.getCreateUserDTOStubFromUser;
import static com.danielqueiroz.fotoradar.stub.UserStub.getUserStub;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
public class UserControllerTest {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ACCESS_TOKEN = "access_token";

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    Integer randomServerPort;

    @Test
    public void testDoLogin() throws Exception {
        User userStub = getUserStub();
        CreateUserDTO createUserDTOStubFromUser = getCreateUserDTOStubFromUser(userStub);
        String userCreated = toGson(userStub);

        mockMvc.perform(post("/api/user/save")
                        .contentType(APPLICATION_JSON)
                        .content(userCreated))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string("{\"username\":\"teste\"}"));

        MvcResult mvcResult = mockMvc.perform(post("/login")
                        .contentType(APPLICATION_FORM_URLENCODED_VALUE)
                        .param(USERNAME, createUserDTOStubFromUser.getUsername())
                        .param(PASSWORD, createUserDTOStubFromUser.getPassword()))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        Assertions.assertNotNull(contentAsString);
//        andExpect(content().string(containsString(ACCESS_TOKEN)))
//                .andExpect(status().isOk());

    }

    @Test
    public void registerUser() throws Exception {
        mockMvc.perform(post("/api/user/save")
                        .contentType(APPLICATION_JSON)
                        .param("username", "daniel")
                        .param("password", "senha123"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string("{\"name\":null,\"username\":\"daniel\",\"email\":null,\"cpf\":null,\"images\":[]}"));

    }

    @Test
    public void registerUserWithToken() throws Exception {
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
    public void registerUserWithToken2() throws Exception {
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
    public void getTokenValue() throws JSONException {
        String token = getToken(randomServerPort);
        Assertions.assertNotNull(token);
        Assertions.assertTrue(token != null);
        Assertions.assertEquals(203, token.length());
    }

    @Test
    public void getUsers() throws Exception {
        mockMvc.perform(get("/api/user")
                        .header(AUTHORIZATION, getToken(randomServerPort)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string("{\"name\":\"da\",\"username\":\"daniel\",\"email\":\"a2@asd\",\"cpf\":null,\"images\":[]}"));

    }

    @Test
    @Transactional
    public void updateUser() throws Exception {
        mockMvc.perform(put("/api/user")
                        .header(AUTHORIZATION, getToken(randomServerPort)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string("{\"name\":\"da\",\"username\":\"daniel\",\"email\":\"a2@asd\",\"cpf\":null,\"images\":[]}"));

    }

}