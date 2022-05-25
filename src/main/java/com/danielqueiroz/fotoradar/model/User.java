package com.danielqueiroz.fotoradar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private String name;
    private String username;

    @JsonIgnore
    private String password;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String cpf;

    @JsonIgnore
    @ManyToMany
    private Collection<Role> roles = new ArrayList<>();

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "notice_id")
    private Collection<Notice> notices = new ArrayList<>();

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "image_id")
    private Collection<Image> images = new ArrayList<>();

}
