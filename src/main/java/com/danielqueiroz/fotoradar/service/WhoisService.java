package com.danielqueiroz.fotoradar.service;

import org.apache.commons.net.whois.WhoisClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WhoisService {

    private static final String WHOIS_SERVER = "whois.iana.org";

    int WHOIS_PORT = WhoisClient.DEFAULT_PORT;

    public void getInfos(String host) throws IOException {
        WhoisClient whois = new WhoisClient();
        try {
            //quando dominios .com.br
//            whois.connect(WHOIS_SERVER, WHOIS_PORT);
            //quando dominios .com .net .org
            whois.connect(WhoisClient.DEFAULT_HOST, WHOIS_PORT);
            String query = whois.query(host);
            System.out.println(query);
        } finally {
            whois.disconnect();
        }
    }

    public static void main(String[] args) throws IOException {
        WhoisService whoisService = new WhoisService();
        whoisService.getInfos("danielqueiroz.com");
    }
}
