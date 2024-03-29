package com.danielqueiroz.fotoradar.conf;

import com.danielqueiroz.fotoradar.filter.CustomAuthenticationFilter;
import com.danielqueiroz.fotoradar.filter.CustomAuthorizationFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
//        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
//        customAuthenticationFilter.setFilterProcessesUrl("/api/user/save");
        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests().antMatchers("/api/status/**").permitAll();


        http.authorizeRequests().antMatchers("/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/**").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/user/save/**").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/image/**", "/api/notice/**", "/api/notice/**").authenticated();
        http.authorizeRequests().antMatchers(GET, "/api/image/**", "/api/user/**", "/api/notice/**", "/api/company/**").authenticated();
        http.authorizeRequests().antMatchers(PUT, "/api/company/**", "/api/notice/**").authenticated();
        http.authorizeRequests().antMatchers(DELETE, "/api/image/**").authenticated();

        http.authorizeRequests().antMatchers(GET, "/api/user**", "/api/image/**", "/api/notice/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers("/api/user/**").authenticated();
        http.authorizeRequests().antMatchers("/api/user/**").authenticated();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
