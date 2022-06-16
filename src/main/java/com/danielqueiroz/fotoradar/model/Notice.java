package com.danielqueiroz.fotoradar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
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
    @Column(name = "notice_id")
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
    private Company company;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "notice")
    private List<Payment> payments = new java.util.ArrayList<>();


}
