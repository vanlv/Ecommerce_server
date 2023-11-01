package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.paymentgateway.VnPay;

@Repository
public interface VnPayConfigRepository extends JpaRepository<VnPay, String> {

}
