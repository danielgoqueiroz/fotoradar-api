package com.danielqueiroz.fotoradar.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

public class Utils {

    public static  String getHash(String url) {
        return Hashing.md5()
                .hashString(url, StandardCharsets.UTF_8)
                .toString();
    }

}
