package com.danielqueiroz.fotoradar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "image_table")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id")
    private Long id;
    private String name;

    @ManyToOne
    private User user;
    private String link;

    @Column(name= "blob_column", length=100000)
    private String blob;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "image_id")
    private Collection<Notice> notices = new ArrayList<>();

}
