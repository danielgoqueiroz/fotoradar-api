package com.danielqueiroz.fotoradar.service;

import com.danielqueiroz.fotoradar.exception.NoticeException;
import com.danielqueiroz.fotoradar.model.*;
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
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.danielqueiroz.fotoradar.utils.Utils.getHash;

@Transactional
@Slf4j
@Service
@AllArgsConstructor
public class NoticeSevice {

    private final ImageRepo imageRepo;
    private final NoticeRepo noticeRepo;
    private final PaymentRepo paymentRepo;
    private final UserRepo userRepo;
    private final CompanyRepo companyRepo;

    public List<Notice> getNotices() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        User user = userRepo.findUserByUsername(username);

        Collection<Notice> allNotices = noticeRepo.findNoticeByUserId(user.getId());
        allNotices.forEach(n -> n.setPayments(paymentRepo.findAllByNoticeId(n.getId())));
        return allNotices.stream().filter(n -> !Objects.isNull(n.getCompany())).collect(Collectors.toList());
    }

    public Notice getnoticeById(Long id) {
        return noticeRepo.findNoticeById(id);
    }

    public Notice save(String username, String url, Long imageId) throws NoticeException, MalformedURLException {
        String hash = getHash(url);
        Notice noticeOnDatabase = noticeRepo.findFirstByLinkHash(hash);
        if(noticeOnDatabase != null) {
           return null;
        }

        String host = new URL(url).getHost();
        Company company = companyRepo.findFirstCompanyByHost(host);
        if (company == null) {
            company = Company.builder()
                    .host(host)
                    .build();
            companyRepo.save(company);
        }
        User user = userRepo.findUserByUsername(username);
        Image image = imageRepo.getById(imageId);

        Notice notice = Notice.builder()
                .user(user)
                .link(url)
                .image(image)
                .linkHash(hash)
                .company(company)
                .build();

        Notice noticeSaved = noticeRepo.save(notice);
        return noticeSaved;
    }

    public void addImageOnNotice(Long idImage, Long idNotice) {
        Image image = imageRepo.getById(idImage);
        Notice notice = getnoticeById(idNotice);
        notice.setImage(image);
    }

    public Notice updateNotice(Notice notice) {
        Notice noticeToUpdate = noticeRepo.findNoticeById(notice.getId());
        noticeToUpdate.setCompany(notice.getCompany());
        noticeToUpdate.setLink(notice.getLink());
        noticeToUpdate.setProcessNumber(notice.getProcessNumber());
        return noticeToUpdate;
    }

    public Notice updateNoticeProcess(Long noticeId, String process) {
        Notice noticeToUpdate = noticeRepo.findNoticeById(noticeId);
        noticeToUpdate.setProcessNumber(process);
        return noticeToUpdate;
    }



    public void addPayment(Long noticeId, BigDecimal value) {
        Notice notice = noticeRepo.findNoticeById(noticeId);
        Payment payment = Payment.builder()
                .date(new Date())
                .value(value)
                .notice(notice)
//                .company(notice.getCompany())
//                .user(notice.getUser())
                .build();
        paymentRepo.save(payment);

    }
}
