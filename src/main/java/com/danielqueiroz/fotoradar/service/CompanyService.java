package com.danielqueiroz.fotoradar.service;

import com.danielqueiroz.fotoradar.api.model.CompanyDTO;
import com.danielqueiroz.fotoradar.model.Company;
import com.danielqueiroz.fotoradar.model.User;
import com.danielqueiroz.fotoradar.repository.CompanyRepo;
import com.danielqueiroz.fotoradar.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Slf4j
@Service
public class CompanyService {

    private final CompanyRepo companyRepo;
    private final UserRepo userRepo;

    public CompanyService(CompanyRepo companyRepo, UserRepo userRepo) {
        this.companyRepo = companyRepo;
        this.userRepo = userRepo;
    }

    public List<Company> findCompanies() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        User user = userRepo.findUserByUsername(username);

        List<Company> companies = companyRepo.findAll(Example.of(
                Company.builder()
                        .user(user)
                        .build()));
        return companies;
    }

    public Company findCompany(String companyId) {
        return companyRepo.findOne(Example.of(
                Company.builder()
                        .id(companyId)
                        .build())
        ).get();
    }

    public Company updateCompany(CompanyDTO company) {
        Company companyToUpdate = companyRepo.findFirstCompanyByHost(company.getHost());
        companyToUpdate.setAddress(company.getAddress());
        companyToUpdate.setEmail(company.getEmail());
        companyToUpdate.setCnpj(company.getCnpj());
        companyToUpdate.setName(company.getName());
        companyToUpdate.setAddress(company.getAddress());
        companyToUpdate.setPhone(company.getPhone());
//        companyToUpdate.setResponsable(company.getSuidResponsable());
        companyRepo.save(companyToUpdate);
        return companyToUpdate;
    }

//    public Notice getnoticeById(Long id) {
//        return noticeRepo.findNoticeById(id);
//    }
//
//    public Notice save(String username, String url) throws NoticeException {
//        String hash = getHash(url);
//        Notice noticeOnDatabase = noticeRepo.findFirstByLinkHash(hash);
//        if(noticeOnDatabase != null) {
//           return null;
//        }
//        User user = userRepo.findByUsername(username);
//
//        Notice notice = Notice.builder()
//                .user(user)
//                .link(url)
//                .linkHash(hash)
//                .build();
//
//        return noticeRepo.save(notice);
//    }
//
//    public void addImageOnNotice(Long idImage, Long idNotice) {
//        Image image = imageRepo.getById(idImage);
//        Notice notice = getnoticeById(idNotice);
//        notice.setImage(image);
//    }
}
