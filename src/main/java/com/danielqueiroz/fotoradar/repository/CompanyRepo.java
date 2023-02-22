package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepo extends MongoRepository<Company, String> {

    Company findFirstCompanyByHost(String host);
    Company findFirstCompanyByName(String name);

}
