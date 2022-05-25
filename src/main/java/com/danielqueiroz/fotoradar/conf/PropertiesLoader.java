package com.danielqueiroz.fotoradar.conf;

public class PropertiesLoader {

    public static String getSecret() {
        String secret = System.getenv("SECRET");
        return secret;
    }

}
