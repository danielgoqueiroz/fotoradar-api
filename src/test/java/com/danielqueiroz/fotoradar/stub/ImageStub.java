package com.danielqueiroz.fotoradar.stub;

import com.danielqueiroz.fotoradar.model.Image;

import static com.danielqueiroz.fotoradar.stub.UserStub.getUserStub;

public class ImageStub {

    public static final String IMG_URL = "https://cbissn.ibict.br/images/phocagallery/galeria2/thumbs/phoca_thumb_l_image03_grd.png";

    public static Image getImageStub() {
        return Image.builder()
                .id("1")
                .link("https://cbissn.ibict.br/images/phocagallery/galeria2/thumbs/phoca_thumb_l_image03_grd.png")
                .name("Google")
                .description("Google")
                .user(getUserStub())
                .build();
    }

    public static Image getImageWithUserStub() {
        return Image.builder()
                .link(IMG_URL)
                .user(getUserStub())
                .build();
    }
}
