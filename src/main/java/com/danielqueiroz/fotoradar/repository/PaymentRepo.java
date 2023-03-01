package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends MongoRepository<Payment, String> {
}
