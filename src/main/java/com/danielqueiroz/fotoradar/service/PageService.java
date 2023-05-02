package com.danielqueiroz.fotoradar.service;

import com.danielqueiroz.fotoradar.exception.NoticeException;
import com.danielqueiroz.fotoradar.model.Process;
import com.danielqueiroz.fotoradar.model.*;
import com.danielqueiroz.fotoradar.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Transactional
@Slf4j
@Service
@AllArgsConstructor
public class PageService {

    private final ImageRepo imageRepo;
    private final PageRepo pageRepo;
    private final PaymentRepo paymentRepo;
    private final UserRepo userRepo;
    private final ProcessRepo processRepo;
    private final CompanyService companyService;
    private final UserService userService;

    public List<Page> getPages() {

        User user = userService.getCurrentUser();

        Page pageExample = Page.builder()
                .user(user)
                .build();
        List<Page> pages = pageRepo.findAll(Example.of(pageExample));
        return pages;
    }

    public Page getPageById(String id) {

        Page pageExample = Page.builder()
                .id(id)
                .image(Image.builder()
                        .user(userService.getCurrentUser())
                        .build())
                .build();
        Page page = pageRepo.findOne(Example.of(pageExample)).get();
        return page;
    }

    public List<Page> getPagesByImgageId(String imageId) throws NoticeException {

        User user = userService.getCurrentUser();

        Image image = imageRepo.findOne(Example.of(Image.builder()
                        .id(imageId)
                        .user(user)
                        .build()))
                .orElseThrow(() -> new NoticeException("Imagem não encontrada"));

        Page pageExample = Page.builder()
                .image(image)
                .company(Company.builder()
                        .user(user)
                        .build())
                .build();
        List<Page> pages = pageRepo.findAll(Example.of(pageExample));
        return pages;
    }

    public void addImageOnPageWithUrl(String imageId, String url) {

        Image image = imageRepo.findImageById(imageId);
        User user = userService.getCurrentUser();

        Company company = companyService.findCompanyByUrl(url);

        pageRepo.findOne(Example.of(Page.builder()
                        .user(user)
                        .url(url)
                        .build()))
                .ifPresentOrElse(page -> {
                    page.setImage(image);
                    pageRepo.save(page);
                }, () -> {
                    Page page = Page.builder()
                            .url(url)
                            .company(company)
                            .user(user)
                            .image(image)
                            .build();
                    pageRepo.save(page);
                });
    }


    public Page addImageOnPageByResponseDTO(Image image, PageResponseDTO responseDTO) {

        Optional<Page> optional = pageRepo.findOne(Example.of(Page.builder()
                .image(image)
                .user(userService.getCurrentUser())
                .url(responseDTO.getPageLink())
                .build()));

        if (optional.isEmpty()) {
            Page page = Page.builder()
                    .image(image)
                    .company(companyService.findCompanyByUrl(responseDTO.getPageLink()))
                    .user(userService.getCurrentUser())
                    .url(responseDTO.getPageLink())
                    .build();
            return pageRepo.save(page);
        }

        return optional.get();
    }

    public Page addImageOnPage(Image image, String idPage) {
        Page page = getPageById(idPage);
        page.setImage(image);
        return pageRepo.save(page);
    }

    public Page addImageOnPage(String idImage, String idPage) {
        Image image = imageRepo.findImageById(idImage);
        Page page = getPageById(idPage);
        page.setImage(image);
        return pageRepo.save(page);
    }

    public Page updatePage(Page page) {
        Page pageToUpdate = pageRepo.findPageById(page.getId());
        pageToUpdate.setCompany(page.getCompany());
        pageToUpdate.setUrl(page.getUrl());
//        noticeToUpdate.setProcessNumber(notice.getProcessNumber());
        return pageToUpdate;
    }

    public Page updatePageProcess(String pageId, String processNumber) {
        Page page = pageRepo.findOne(Example.of(Page.builder()
                .id(pageId)
                .build())).get();

        if (page.getProcess() == null) {
            Process process = processRepo.save(Process.builder()
                    .processNumber(processNumber)
                    .pages(Arrays.asList(page))
                    .user(page.getImage().getUser())
                    .build());
            page.setProcess(process);
            return pageRepo.save(page);
        } else {
            page.getProcess().setProcessNumber(processNumber);
            return pageRepo.save(page);
        }

    }
}


//    public void addPayment(String pageId, BigDecimal value) {
//        Page page = pageRepo.findPageById(pageId);
//        Process process = processRepo.findProcessByPageId(pageId);
//        Payment payment = Payment.builder()
//                .date(new Date())
//                .value(value)
//                .build();
//        paymentRepo.save(payment);
//    }

