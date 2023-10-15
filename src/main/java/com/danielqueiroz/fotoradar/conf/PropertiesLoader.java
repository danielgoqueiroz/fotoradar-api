package com.danielqueiroz.fotoradar.conf;

import org.springframework.stereotype.Component;

import static java.lang.System.getenv;
import static java.util.Optional.of;
import static org.apache.logging.log4j.util.Strings.EMPTY;


@Component
public class PropertiesLoader {

    public static String getSecret() {
        return of(getenv("SECRET"))
                .orElse(EMPTY);
    }

}
