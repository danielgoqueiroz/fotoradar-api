package com.danielqueiroz.fotoradar.service;

import com.danielqueiroz.fotoradar.exception.NoticeException;
import com.danielqueiroz.fotoradar.model.*;
import com.danielqueiroz.fotoradar.model.Process;
import com.danielqueiroz.fotoradar.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static com.danielqueiroz.fotoradar.utils.Utils.getHash;

@Transactional
@Slf4j
@Service
@AllArgsConstructor
public class PageSevice {

    private final ImageRepo imageRepo;
    private final PageRepo pageRepo;
    private final PaymentRepo paymentRepo;
    private final UserRepo userRepo;
    private final ProcessRepo processRepo;
    private final CompanyRepo companyRepo;

    public List<Page> getPages() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        User user = userRepo.findUserByUsername(username);

        User userFinder = User.builder()
                .id(user.getId())
                .build();
        Page pageExample = Page.builder()
                .image(Image.builder()
                        .user(userFinder)
                        .build())
                .build();
        List<Page> pages = pageRepo.findAll(Example.of(pageExample));
        return pages;
    }

    public List<Page> getPagesByImgageId(String imageId) throws NoticeException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();

        User user = userRepo.findUserByUsername(username);
        Image image = imageRepo.findOne(Example.of(Image.builder()
                        .id(imageId)
                        .user(user)
                        .build()))
                .orElseThrow(() -> new NoticeException("Imagem não encontrada"));

        Page pageExample = Page.builder()
                .image(image)
                .build();
        List<Page> pages = pageRepo.findAll(Example.of(pageExample));
        return pages;
    }

    public Page getPageById(String id) {
        return pageRepo.findPageById(id);
    }

    public void addImageOnPageWithUrl(String imageId, String url) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();

        Image image = imageRepo.findImageById(imageId);

        Company company = getCompany(url);

        pageRepo.findOne(Example.of(Page.builder().url(url).build()))
                .ifPresentOrElse(page -> {
                    page.setImage(image);
                    pageRepo.save(page);
                }, () -> {
                    Page page = Page.builder()
                            .url(url)
                            .company(company)
                            .image(image)
                            .build();
                    pageRepo.save(page);
                });
    }

    private Company getCompany(String url) {
        Optional<Company> companyOptional = companyRepo.findOne(Example.of(Company.builder()
                .host(getHost(url))
                .build()));

        if (companyOptional.isPresent()) {
            return companyOptional.get();
        } else {
            Company company = Company.builder()
                    .host(getHost(url))
                    .build();
            companyRepo.save(company);
            return company;
        }
    }

    private String getHost(String url) {
        URL netUrl = null;
        try {
            netUrl = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return netUrl.getHost();
    }

    public void addImageOnPage(String idImage, String idNotice) {
        Image image = imageRepo.findImageById(idImage);
        Page page = getPageById(idNotice);
        page.setImage(image);
        pageRepo.save(page);
    }

    public Page updatePage(Page page) {
        Page pageToUpdate = pageRepo.findPageById(page.getId());
        pageToUpdate.setCompany(page.getCompany());
        pageToUpdate.setUrl(page.getUrl());
//        noticeToUpdate.setProcessNumber(notice.getProcessNumber());
        return pageToUpdate;
    }

    public Page updatePageProcess(String noticeId, String processNumber) {
        Page pageToUpdate = pageRepo.findPageById(noticeId);
//        if(pageToUpdate.getProcess() == null) {
//            Process process = Process.builder()
//                    .processNumber(processNumber).build();
//            Process processByProcessNumber = processRepo.findProcessByProcessNumber(processNumber);
//            if (processByProcessNumber == null) {
//                Process processSaved = processRepo.save(process);
//                pageToUpdate.setProcess(processSaved);
//            }
//
//        };

        return pageToUpdate;
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
}
