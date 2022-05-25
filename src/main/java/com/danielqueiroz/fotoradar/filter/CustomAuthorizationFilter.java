package com.danielqueiroz.fotoradar.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.danielqueiroz.fotoradar.Contants.Const;
import com.danielqueiroz.fotoradar.conf.PropertiesLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.danielqueiroz.fotoradar.Contants.Const.BEARER;
import static com.danielqueiroz.fotoradar.Contants.Const.ROLES;
import static com.danielqueiroz.fotoradar.conf.PropertiesLoader.getSecret;
import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/api/dev")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (request.getServletPath().equals("/api/login")) {
            filterChain.doFilter(request, response);
            return;
        } else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
                try {
                    String token = authorizationHeader.substring(BEARER.length());
                    Algorithm algorithm = Algorithm.HMAC256(getSecret().getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim(ROLES).asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    if (roles != null) {
                        stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
                    }
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    logger.error("Erro durante login. Erro: {}", e);
                    response.setHeader("Error", e.getMessage());
                    response.setStatus(FORBIDDEN.value());

                    Map<String, String> error = new HashMap<>();
                    error.put("erro_message", e.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                    filterChain.doFilter(request, response);
                    return;
                }
            } else {
                String message = "Token não informado";
                logger.warn(message);
                response.setHeader("Error", message);
                response.setStatus(UNAUTHORIZED.value());
                filterChain.doFilter(request, response);
                return;
            }
        }
    }
}
