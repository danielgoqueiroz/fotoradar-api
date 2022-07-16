package com.danielqueiroz.fotoradar.api.model;

import lombok.*;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageDTO {

    private Long imageId;
    private List<String> links;
}
