package com.danielqueiroz.fotoradar.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "attorney")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attorney {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @OneToOne
    private User user;

    private String oabNumber;

    @OneToMany
    private Collection<Process> processes;

}
