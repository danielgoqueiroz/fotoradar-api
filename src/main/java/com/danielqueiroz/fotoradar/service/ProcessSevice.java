package com.danielqueiroz.fotoradar.service;

import com.danielqueiroz.fotoradar.exception.NoticeException;
import com.danielqueiroz.fotoradar.model.*;
import com.danielqueiroz.fotoradar.model.Process;
import com.danielqueiroz.fotoradar.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.danielqueiroz.fotoradar.utils.Utils.getHash;

@Transactional
@Slf4j
@Service
@AllArgsConstructor
public class ProcessSevice {

    private final ProcessRepo processRepo;

    public List<Process> getProcesses() {

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username = (String) auth.getPrincipal();
//        User user = userRepo.findUserByUsername(username);

        List<Process> processes = processRepo.findAll();
        return processes;
    }

    public void addPayment(String processId, String value) {
        Process process = processRepo.findProcessByProcessNumber(processId);
        process.getPayments().add(Payment.builder()
                .value(new BigDecimal(value))
                .build());

        processRepo.save(process);
    }



//    public Page getPageById(String id) {
//        return pageRepo.findPageById(id);
//    }
//
//    public Page save(String username, String url, String imageId) throws NoticeException, MalformedURLException {
//        String hash = getHash(url);
//
//        String host = new URL(url).getHost();
//        Company company = companyRepo.findFirstCompanyByHost(host);
//        if (company == null) {
//            company = Company.builder()
//                    .host(host)
//                    .build();
//            companyRepo.save(company);
//        }
//        User user = userRepo.findUserByUsername(username);
//        Image image = imageRepo.findImageById(imageId);
//
//        Page page = Page.builder()
//                .url(url)
//                .image(image)
//                .company(company)
//                .build();
//
//        Page pageSaved = pageRepo.save(page);
//        return pageSaved;
//    }
//

//
//    public Page updatePage(Page page) {
//        Page pageToUpdate = pageRepo.findPageById(page.getId());
//        pageToUpdate.setCompany(page.getCompany());
//        pageToUpdate.setUrl(page.getUrl());
////        noticeToUpdate.setProcessNumber(notice.getProcessNumber());
//        return pageToUpdate;
//    }
//
//    public Page updatePageProcess(String noticeId, String processNumber) {
//        Page pageToUpdate = pageRepo.findPageById(noticeId);
////        if(pageToUpdate.getProcess() == null) {
////            Process process = Process.builder()
////                    .processNumber(processNumber).build();
////            Process processByProcessNumber = processRepo.findProcessByProcessNumber(processNumber);
////            if (processByProcessNumber == null) {
////                Process processSaved = processRepo.save(process);
////                pageToUpdate.setProcess(processSaved);
////            }
////
////        };
//
//        return pageToUpdate;
//    }


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
