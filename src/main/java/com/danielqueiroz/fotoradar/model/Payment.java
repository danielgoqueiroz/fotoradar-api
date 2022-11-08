package com.danielqueiroz.fotoradar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
@Setter
public class Payment {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private Date date;
    private BigDecimal value;

//    @ManyToOne
    @JsonBackReference
//    @JoinColumn(name = "page_id")
    private Page page;

//    @ManyToOne
    @JsonManagedReference
//    @JoinColumn
    private Company company;

}