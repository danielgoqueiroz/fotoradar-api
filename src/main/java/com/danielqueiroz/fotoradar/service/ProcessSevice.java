package com.danielqueiroz.fotoradar.service;

import com.danielqueiroz.fotoradar.model.Process;
import com.danielqueiroz.fotoradar.model.*;
import com.danielqueiroz.fotoradar.repository.ProcessRepo;
import com.danielqueiroz.fotoradar.repository.UserRepo;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Proc;
import org.bson.BsonDocument;
import org.bson.Document;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.Doc;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Slf4j
@Service
@AllArgsConstructor
public class ProcessSevice {

    private final ProcessRepo processRepo;
    private final UserRepo userRepo;

    private final MongoTemplate mongoTemplate;

    public Collection<? super Document> getProcesses() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        User user = userRepo.findUserByUsername(username);

        FindIterable<Document> documents = mongoTemplate.getCollection("process")
                .find(Criteria.where("pages.image.name").is("31").getCriteriaObject());
        documents.forEach(process -> {
            System.out.println(process);
        });

        try {
//            processes = process.find(new Document("pages", new Document("$elemMatch", new Document("image", new Document("user", user.getId()))))).into(new ArrayList<>());


        } catch (Exception e) {
            log.error("Error to get processes", e);
        }
        return null;
    }

    public Process addPayment(String processId, String value, String date) throws ParseException {
        Process process = processRepo.findOne(Example.of(Process.builder()
                .id(processId).build())).get();
        process.getPayments().add(Payment.builder()
                .value(new BigDecimal(value))
                .date(new SimpleDateFormat("yyyy-MM-dd").parse(date))
                .build());

        return processRepo.save(process);
    }

    public Process updateProcessNumber(String id, String processNumber) {
        Optional<Process> processOpt = processRepo.findOne(Example.of(Process.builder().id(id).build()));
        if (processOpt.isPresent()) {
            Process process = processOpt.get();
            process.setProcessNumber(processNumber);
            processRepo.save(process);
            return process;
        }
        return null;

    }

    public Process getProcess(String id) {
        return processRepo.findOne(Example.of(Process.builder().id(id).build())).get();
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
