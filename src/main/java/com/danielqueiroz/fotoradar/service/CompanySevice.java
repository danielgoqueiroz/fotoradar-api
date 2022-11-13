package com.danielqueiroz.fotoradar.service;

import com.danielqueiroz.fotoradar.api.model.CompanyDTO;
import com.danielqueiroz.fotoradar.model.Company;
import com.danielqueiroz.fotoradar.repository.CompanyRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Slf4j
@Service
public class CompanySevice {

    private final CompanyRepo companyRepo;

    public CompanySevice(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    public List<Company> findCompanies() {
        List<Company> companies = companyRepo.findAll();
        return companies;
    }

    public Company updateCompany(CompanyDTO company) {
        Company companyToUpdate = companyRepo.findFirstCompanyByHost(company.getHost());
        companyToUpdate.setAddress(company.getAddress());
        companyToUpdate.setEmail(company.getMail());
        companyToUpdate.setCnpj(company.getCnpj());
        companyToUpdate.setName(company.getName());
        companyToUpdate.setAddress(company.getAddress());
        companyToUpdate.setPhone(company.getPhone());
//        companyToUpdate.setResponsable(company.getSuidResponsable());
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
