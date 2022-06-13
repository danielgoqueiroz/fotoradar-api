package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Company;
import com.danielqueiroz.fotoradar.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company, Long> {

    Company findFirstCompanyByHost(String host);
    Company findFirstCompanyByName(String name);

}
