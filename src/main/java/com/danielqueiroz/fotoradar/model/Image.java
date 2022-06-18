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
@Table(name = "image")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    private User user;
    private String link;

    @JsonIgnore
    @Column(name = "blob_column", length = 100000)
    private String blob;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "image_id")
    private Collection<Notice> notices = new ArrayList<>();

}
