package com.danielqueiroz.fotoradar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hibernate.annotations.NotFoundAction.IGNORE;

@Data
@Entity
@Table(name = "notice")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    private String link;

    private String processNumber;

    @JsonIgnore
    private String linkHash;

    @ManyToOne
    @NotFound(action = IGNORE)
    private Image image;

    @ManyToOne
    @JsonManagedReference
    private Company company;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "notice")
    @JsonManagedReference
    private Collection<Payment> payments = new ArrayList<>();


}
