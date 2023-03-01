package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends MongoRepository<Company, String> {

    Company findFirstCompanyByHost(String host);
    Company findFirstCompanyByName(String name);

}
