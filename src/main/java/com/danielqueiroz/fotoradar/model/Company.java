package com.danielqueiroz.fotoradar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id")
    private Long id;

    private String name;
    private String cnpj;
    private String suidResponsable;
    private String phone;
    private String address;
    private String host;
    private String mail;

    @OneToMany
    @JoinColumn(name = "company_id")
    private List<Notice> notices;

}