package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentRepo extends MongoRepository<Payment, String> {

    List<Payment> findAllByPageId(Long noticeId);
}
