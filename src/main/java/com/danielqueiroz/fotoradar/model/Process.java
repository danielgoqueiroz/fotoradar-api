package com.danielqueiroz.fotoradar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "process")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String processNumber;
    private Date processDate;

    @OneToMany
    @JoinColumn(name = "page_id")
    private Collection<Page> pages;

    @OneToOne
    @JoinColumn(name = "attorney_id")
    private Attorney attorney;

}
