package com.danielqueiroz.fotoradar.service;

import com.danielqueiroz.fotoradar.api.model.CompanyDTO;
import com.danielqueiroz.fotoradar.model.Company;
import com.danielqueiroz.fotoradar.model.User;
import com.danielqueiroz.fotoradar.repository.CompanyRepo;
import com.danielqueiroz.fotoradar.repository.UserRepo;
import com.danielqueiroz.fotoradar.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.danielqueiroz.fotoradar.utils.Utils.getHost;

@Transactional
@Slf4j
@Service
public class CompanyService {

    private final CompanyRepo companyRepo;
    private final UserService userService;

    public CompanyService(CompanyRepo companyRepo, UserService userService) {
        this.companyRepo = companyRepo;
        this.userService = userService;
    }

    public List<Company> findCompanies() {
        User user = userService.getCurrentUser();

        List<Company> companies = companyRepo.findAll(Example.of(
                Company.builder()
                        .user(user)
                        .build()));
        return companies;
    }

    public Company findCompany(String companyId) {
        User user = userService.getCurrentUser();
        Company company = companyRepo.findOne(Example.of(
                Company.builder()
                        .id(companyId)
                        .user(user)
                        .build())
        ).get();
        return company;
    }

    public Company updateCompany(CompanyDTO company) {

        User user = userService.getCurrentUser();

        Company companyToUpdate = companyRepo.findOne(Example.of(Company.builder()
                .user(user)
                .build())).get();

        companyToUpdate.setCnpj(company.getCnpj());
        companyToUpdate.setName(company.getName());
        companyRepo.save(companyToUpdate);
        return companyToUpdate;
    }

    public Company findCompanyByUrl(String url) {

        String host = getHost(url);
        User user = userService.getCurrentUser();

        Optional<Company> companyOptional = companyRepo.findOne(Example.of(Company.builder()
                .user(user)
                .host(host)
                .build()));

        if (companyOptional.isPresent()) {
            return companyOptional.get();
        } else {
            Company company = Company.builder()
                    .host(getHost(url))
                    .user(user)
                    .build();
            return companyRepo.save(company);
        }
    }

}
