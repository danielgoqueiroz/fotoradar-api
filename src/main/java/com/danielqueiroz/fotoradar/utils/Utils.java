package com.danielqueiroz.fotoradar.utils;

import com.google.common.hash.Hashing;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

public class Utils {

    public static  String getHash(String url) {
        return Hashing.md5()
                .hashString(url, StandardCharsets.UTF_8)
                .toString();
    }

    public static String getHost(String url) {
        URL netUrl = null;
        try {
            netUrl = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return netUrl.getHost();
    }

}
