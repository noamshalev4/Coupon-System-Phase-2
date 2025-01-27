package com.example.CouponSystem2.services;

import com.example.CouponSystem2.repositories.CompanyRepository;
import com.example.CouponSystem2.repositories.CouponRepository;
import com.example.CouponSystem2.repositories.CustomerRepository;
import com.example.CouponSystem2.security.LoginResponse;
import com.example.CouponSystem2.security.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CouponRepository couponRepository;
    @Autowired
    protected TokenManager tokenManager;

    public abstract LoginResponse login(String email, String password);
}
