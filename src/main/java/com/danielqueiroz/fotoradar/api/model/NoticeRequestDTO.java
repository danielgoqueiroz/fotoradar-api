package com.danielqueiroz.fotoradar.api.model;

import lombok.*;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeRequestDTO {

    private Long imageId;
    private List<String> links;
}
