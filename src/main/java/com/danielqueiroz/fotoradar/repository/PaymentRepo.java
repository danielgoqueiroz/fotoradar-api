package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepo extends JpaRepository<Payment, Long> {

    List<Payment> findAllByPageId(Long noticeId);
}
