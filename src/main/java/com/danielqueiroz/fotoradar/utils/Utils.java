package com.danielqueiroz.fotoradar.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Utils {

    public static  String getHash(String url) {
        return Hashing.md5()
                .hashString(url, StandardCharsets.UTF_8)
                .toString();
    }
}
