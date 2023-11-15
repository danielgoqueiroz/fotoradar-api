package com.danielqueiroz.fotoradar.service;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class WhoisServiceTest {

    @Test
    void getInfos() throws IOException {
        WhoisService whois = new WhoisService();
        String infos = whois.getInfos("google.com");
        assertNotNull(infos);
    }
}