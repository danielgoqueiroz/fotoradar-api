package com.danielqueiroz.fotoradar.conf;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.lang.System.getenv;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.util.Strings.EMPTY;


@Component
public class PropertiesLoader {

    public static String getSecret() {
        String secret = getenv("SECRET");
        Optional<String> secretOptional = ofNullable(secret);
        if (secretOptional.isPresent()) {
            return secretOptional.get();
        }
        return "key";
    }

}
