package com.danielqueiroz.fotoradar.model;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDTO {

    private String title;
    private String pageLink;
    private String imageLink;

}
