package com.danielqueiroz.fotoradar.api.controller;

import com.danielqueiroz.fotoradar.api.model.CreateUserDTO;
import com.danielqueiroz.fotoradar.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static com.danielqueiroz.fotoradar.api.TestUtils.toGson;
import static com.danielqueiroz.fotoradar.stub.CreateUserDTOStub.getCreateUserDTOStubFromUser;
import static com.danielqueiroz.fotoradar.stub.UserStub.getUserStub;
import static com.danielqueiroz.fotoradar.stub.UserStub.getUserUpdatedStub;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
public class UserControllerTest {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    Integer randomServerPort;

    @BeforeEach
    public void setupAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthenticationToken("key", "teste", AuthorityUtils
                .createAuthorityList("ROLE_USER", "ROLE_ADMIN")));
    }

    @Test
    public void shouldCreateUser() throws Exception {
        User userStub = getUserStub();
        String userCreated = toGson(userStub);
        createUser(userCreated);
    }

    @Test
    public void shouldGenerateToken() throws Exception {
        assertNotNull(createUserAndGetToken());
    }

    @Test
    public void updateUser() throws Exception {
        User userStub = getUserStub();
        String updatedUser = toGson(getUserUpdatedStub());
        createUser(toGson(userStub));

        mockMvc.perform(put("/api/user")
                        .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .content(updatedUser)
                        .with(user(userStub.getUsername()).roles("USER").password(userStub.getPassword()))
                )
                .andExpect(status().isAccepted());
    }

    private void createUser(String userCreated) throws Exception {
        mockMvc.perform(post("/api/user/save")
                        .contentType(APPLICATION_JSON)
                        .content(userCreated))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(content().string("{\"name\":null,\"username\":\"teste\",\"email\":null,\"cpf\":null,\"images\":null}"));
    }

    private String doLoginAndGetToken(CreateUserDTO createUserDTOStubFromUser) throws Exception {
        return mockMvc.perform(post("/login")
                        .contentType(APPLICATION_FORM_URLENCODED_VALUE)
                        .param(USERNAME, createUserDTOStubFromUser.getUsername())
                        .param(PASSWORD, createUserDTOStubFromUser.getPassword()))
                .andReturn()
                .getResponse().getContentAsString();
    }

    private String createUserAndGetToken() throws Exception {
        User userStub = getUserStub();
        CreateUserDTO createUserDTOStubFromUser = getCreateUserDTOStubFromUser(userStub);
        String userCreated = toGson(userStub);

        createUser(userCreated);
        return doLoginAndGetToken(createUserDTOStubFromUser);
    }


}