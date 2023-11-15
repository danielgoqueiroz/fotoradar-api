package com.danielqueiroz.fotoradar.service;

import com.danielqueiroz.fotoradar.exception.ImageExceptions;
import com.danielqueiroz.fotoradar.model.Image;
import com.danielqueiroz.fotoradar.model.User;
import com.danielqueiroz.fotoradar.repository.ImageRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import static com.danielqueiroz.fotoradar.stub.ImageStub.getImageStub;
import static com.danielqueiroz.fotoradar.stub.UserStub.getUserStub;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceTest {

    public static final String IMG_NAME = "Google";
    public static final User USER = getUserStub();
    public static final Image IMAGE = getImageStub();
    @Mock
    private ImageRepo imageRepo;
    @Mock
    private UserService userService;
    @Mock
    private PageService pageService;
    @Mock
    private ImageSearchService imageSearchService;
    @InjectMocks
    private ImageService imageService;

    @BeforeEach
    void setUp() {
        when(userService.getCurrentUser()).thenReturn(USER);
    }

    @Test
    void whenSaveImageWithMalformedUrlShouldThrowError() {
        IMAGE.setLink("Http://malformedUrl.com");
        when(imageRepo.findOne(any())).thenReturn(empty());

        assertThrows(ImageExceptions.class, () -> imageService.saveImage(IMAGE.getLink(), IMG_NAME));
    }

//    @Test
//    void saveImage() throws Exception {
//        when(imageRepo.findOne(any())).thenReturn(empty());
//        when(imageRepo.save(any())).thenReturn(IMAGE);
//        Image image = imageService.saveImage(IMAGE.getLink(), IMG_NAME);
//        assertNotNull(image);
//    }
//
//    @Test
//    void saveImageThenExists() throws Exception {
//        when(userService.getCurrentUser()).thenReturn(USER);
//        Image imageQuery = Image.builder()
//                .link(IMAGE.getLink())
//                .user(IMAGE.getUser())
//                .build();
//        when(imageRepo.findOne(Example.of(imageQuery))).thenReturn(of(IMAGE));
//
//        Image image = imageService.saveImage(IMAGE.getLink(), IMG_NAME);
//
//        assertNotNull(image);
//
//        verify(imageRepo, times(0)).save(any());
//    }
//
//    @Test
//    void findImageByLink() {
//    }
//
//    @Test
//    void findAll() {
//    }
//
//    @Test
//    void delete() {
//    }
//
//    @Test
//    void findImage() {
//    }
}