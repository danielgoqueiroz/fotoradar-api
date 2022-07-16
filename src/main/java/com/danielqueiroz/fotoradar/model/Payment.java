package com.danielqueiroz.fotoradar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "payment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private BigDecimal value;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "page_id")
    private Page page;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn
    private Company company;

}