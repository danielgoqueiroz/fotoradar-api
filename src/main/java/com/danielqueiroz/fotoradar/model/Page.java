package com.danielqueiroz.fotoradar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static org.hibernate.annotations.NotFoundAction.IGNORE;

@Data
@Entity
@Table(name = "notice")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String url;

    @ManyToOne
    @JsonManagedReference
    private Image image;

    @ManyToOne
    @JsonManagedReference
    private Company company;

    @ManyToOne
    @JsonManagedReference
    private Process process;

}
