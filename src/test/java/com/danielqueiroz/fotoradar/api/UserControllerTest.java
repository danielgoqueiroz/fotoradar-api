package com.danielqueiroz.fotoradar.api;

import com.danielqueiroz.fotoradar.api.coltroller.UserController;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Hello World!")));

    }

    @Test
    void saveUser() {
    }

    @Test
    void testSaveUser() {
    }

    @Test
    void addToUser() {
    }
}